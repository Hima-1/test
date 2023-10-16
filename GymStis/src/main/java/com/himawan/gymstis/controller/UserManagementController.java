package com.himawan.gymstis.controller;

import com.himawan.gymstis.dto.ProfileChangePasswordRequest;
import com.himawan.gymstis.dto.ProfileEditRequest;
import com.himawan.gymstis.dto.ProfileResponse;
import com.himawan.gymstis.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserManagementController {
    private final UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Delete user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        try {
            ProfileResponse user = userService.deleteUser(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Edit user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile edited", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> editProfile(@Valid @RequestBody ProfileEditRequest request, @PathVariable Long id) {
        try {
            ProfileResponse user = userService.changeProfile(id ,request.getName(), request.getEmail(), request.getGender());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Change user password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Password changed", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProfileResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PatchMapping("{id}/password")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody ProfileChangePasswordRequest request, @PathVariable Long id) {
        try {
            ProfileResponse user = userService.changePassword(id, request.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
