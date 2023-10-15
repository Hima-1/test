package com.himawan.gymstis.controller;

import com.himawan.gymstis.dto.ProfileChangePasswordRequest;
import com.himawan.gymstis.dto.ProfileEditRequest;
import com.himawan.gymstis.dto.ProfileResponse;
import com.himawan.gymstis.entity.User;
import com.himawan.gymstis.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/profile")
public class UserProfileController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public ResponseEntity<Object> getProfile() {
        try {
            User user = userService.getUserLogged();
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteProfile(Authentication authentication,
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

    @PutMapping("")
    public ResponseEntity<Object> editProfile(@Valid @RequestBody ProfileEditRequest request) {
        try {
            ProfileResponse user = userService.changeProfile(request.getName(), request.getEmail(), request.getGender());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody ProfileChangePasswordRequest request) {
        try {
            ProfileResponse user = userService.changePassword(request.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
