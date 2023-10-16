package com.himawan.gymstis.controller;

import com.himawan.gymstis.dto.ProfileChangePasswordRequest;
import com.himawan.gymstis.dto.ProfileEditRequest;
import com.himawan.gymstis.dto.ProfileResponse;
import com.himawan.gymstis.entity.User;
import com.himawan.gymstis.mapper.UserMapper;
import com.himawan.gymstis.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
public class UserProfileController {
    final
    UserService userService;

    public UserProfileController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get current user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile retrieved", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping
    public ResponseEntity<Object> getProfile() {
        try {
            User user = userService.getUserLogged();
            ProfileResponse profileResponse = UserMapper.toProfileResponse(user);
            return ResponseEntity.ok(profileResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Delete current user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile deleted", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @DeleteMapping
    public ResponseEntity<Object> deleteProfile(Authentication authentication,
                                                HttpServletRequest request,
                                                HttpServletResponse response) {
        try {
            userService.deleteCurrentUser();
            new SecurityContextLogoutHandler().logout(request,
                    response,
                    authentication);
            return ResponseEntity.ok("Account deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Edit current user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile edited", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PutMapping
    public ResponseEntity<Object> editProfile(@Valid @RequestBody ProfileEditRequest request) {
        try {
            ProfileResponse user = userService.changeProfile(request.getName(), request.getEmail(), request.getGender());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Change current profile password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password changed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PatchMapping("/password")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody ProfileChangePasswordRequest request) {
        try {
            ProfileResponse user = userService.changePassword(request.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
