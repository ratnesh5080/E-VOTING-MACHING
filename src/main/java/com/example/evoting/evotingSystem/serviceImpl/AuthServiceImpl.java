package com.example.evoting.evotingSystem.serviceImpl;

import com.example.evoting.evotingSystem.model.OtpVerification;
import com.example.evoting.evotingSystem.model.User;
import com.example.evoting.evotingSystem.repository.OtpRepository;
import com.example.evoting.evotingSystem.repository.UserRepository;
import com.example.evoting.evotingSystem.service.AuthService;
import com.example.evoting.evotingSystem.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public User registerUser(User user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered!");
        }
        user.setVerified(false);
        return userRepository.save(user);
    }
    @Override
    public User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElse(null);
    }

    @Override
    public String sendOtp(String email) {
        // Generate 6-digit OTP
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        // Save or update OTP entry
        Optional<OtpVerification> existingOtp = otpRepository.findByEmail(email);
        OtpVerification otpVerification;
        if (existingOtp.isPresent()) {
            otpVerification = existingOtp.get();
            otpVerification.setOtp(otp);
            otpVerification.setGeneratedTime(LocalDateTime.now());
        } else {
            otpVerification = new OtpVerification(email, otp, LocalDateTime.now());
        }
        otpRepository.save(otpVerification);

        // Send email
        String subject = "E-Voting System - OTP Verification";
        String message = "Your OTP for email verification is: " + otp;
        emailService.sendEmail(email, subject, message);

        return "OTP sent successfully!";
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        Optional<OtpVerification> otpVerification = otpRepository.findByEmail(email);

        if (otpVerification.isPresent() && otpVerification.get().getOtp().equals(otp)) {
            // Mark user as verified
            User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found!"));
            user.setVerified(true);
            userRepository.save(user);

            // Delete OTP after successful verification
            otpRepository.deleteByEmail(email);
            return true;
        }
        return false;
    }

    @Override
    public boolean login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && user.get().getPassword().equals(password) && user.get().isVerified();
    }
}
