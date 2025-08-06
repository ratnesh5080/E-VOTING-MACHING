package com.example.evoting.evotingSystem.service;

import com.example.evoting.evotingSystem.model.User;

public interface AuthService {

    // Register user (returns saved user)
    User registerUser(User user);

    // Generate and send OTP to email
    String sendOtp(String email);
     
    User getUserByEmail(String email);

    // Verify OTP
    boolean verifyOtp(String email, String otp);

    // Login (returns JWT or simple message - for now boolean)
    boolean login(String email, String password);
}
