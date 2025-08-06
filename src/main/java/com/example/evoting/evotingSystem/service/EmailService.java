package com.example.evoting.evotingSystem.service;

public interface EmailService {

    // Send email with subject and message
    void sendEmail(String to, String subject, String text);
}

