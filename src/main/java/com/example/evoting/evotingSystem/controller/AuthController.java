package com.example.evoting.evotingSystem.controller;

import com.example.evoting.evotingSystem.model.LoginRequest;
import com.example.evoting.evotingSystem.model.User;
import com.example.evoting.evotingSystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ========== 1. Register User ==========
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User savedUser = authService.registerUser(user);
        String message = authService.sendOtp(user.getEmail());  // send OTP immediately
        Map<String, Object> response = new HashMap<>();
        response.put("userId", savedUser.getId());
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    // ========== 2. Verify OTP ==========
    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isVerified = authService.verifyOtp(email, otp);
        Map<String, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("verified", isVerified);
        return ResponseEntity.ok(response);
    }

    // ========== 3. Login ==========
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        boolean isLogin = authService.login(email, password);
        Map<String, Object> response = new HashMap<>();
        response.put("email", email);
        response.put("login", isLogin);

        // Assuming your User entity has a getRole method that returns "ADMIN", "VOTER", or "CANDIDATE"
        if (isLogin) {
            User user = authService.getUserByEmail(email);
            if (user != null) {
                response.put("role", user.getRole());
            } else {
                response.put("role", "VOTER"); // default role fallback
            }
        } else {
            response.put("role", null);
        }
        return ResponseEntity.ok(response);
    }
}
