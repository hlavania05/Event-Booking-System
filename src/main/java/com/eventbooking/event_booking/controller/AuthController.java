package com.eventbooking.event_booking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eventbooking.event_booking.dto.SignupRequestDto;
import com.eventbooking.event_booking.dto.ApiResponse;
import com.eventbooking.event_booking.dto.AuthResponseDto;
import com.eventbooking.event_booking.dto.LoginRequestDto;
import com.eventbooking.event_booking.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerUser(@Valid @RequestBody SignupRequestDto request) {
        try {
            AuthResponseDto response = authService.registerUser(
                    request.getName(), request.getEmail(), request.getPassword());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new AuthResponseDto("Registration Failed: " + e.getMessage(), null),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        try {
            String token = authService.authenticateUser(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(new AuthResponseDto("Login Successful!", token));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponseDto("Login Failed: " + ex.getMessage(), null));
        }
    }
}
