package com.example.evoting.evotingSystem.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    // Create poll
    @PostMapping("/poll")
    public String createPoll() {
        return "Create poll API - logic pending";
    }

    // Activate or deactivate poll
    @PutMapping("/poll/{pollId}/status")
    public String changePollStatus(@PathVariable Long pollId) {
        return "Change poll status API - logic pending for pollId: " + pollId;
    }

    // View results
    @GetMapping("/poll/{pollId}/results")
    public String getPollResults(@PathVariable Long pollId) {
        return "Poll results API - logic pending for pollId: " + pollId;
    }
}
