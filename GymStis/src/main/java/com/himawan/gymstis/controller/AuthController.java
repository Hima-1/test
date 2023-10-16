package com.himawan.gymstis.controller;

import com.himawan.gymstis.dto.AuthRequest;
import com.himawan.gymstis.dto.AuthResponse;
import com.himawan.gymstis.dto.UserDTO;
import com.himawan.gymstis.security.JwtUtils;
import com.himawan.gymstis.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {
    final
    AuthenticationManager authManager;
    final
    JwtUtils jwtUtil;
    final
    UserService userService;

    public AuthController(AuthenticationManager authManager, JwtUtils jwtUtil, UserService userService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Operation(summary = "Login existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Credentials", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
            String accessToken = jwtUtil.generateAccessToken(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDTO userDetails = (UserDTO) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            AuthResponse response = new AuthResponse(request.getEmail(), accessToken, roles);
            return ResponseEntity.ok().body(response);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @Operation(summary = "register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class))})})
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO request) {
        UserDTO user = userService.createUser(request);
        return ResponseEntity.ok().body(user);
    }
}
