package com.himawan.gymstis.controller;

import com.himawan.gymstis.dto.ProfileChangePasswordRequest;
import com.himawan.gymstis.dto.ProfileEditRequest;
import com.himawan.gymstis.dto.ProfileResponse;
import com.himawan.gymstis.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @DeleteMapping("/delete-account/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("User with id " + id + " is deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editProfile(@Valid @RequestBody ProfileEditRequest request, @PathVariable Long id) {
        try {
            ProfileResponse user = userService.changeProfile(id ,request.getName(), request.getEmail(), request.getGender());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<Object> changePassword(@Valid @RequestBody ProfileChangePasswordRequest request, @PathVariable Long id) {
        try {
            ProfileResponse user = userService.changePassword(id, request.getPassword());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
