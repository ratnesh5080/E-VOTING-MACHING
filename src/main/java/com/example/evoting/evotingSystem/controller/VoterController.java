package com.example.evoting.evotingSystem.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/voter")
public class VoterController {

    // Get active polls for voter
    @GetMapping("/polls")
    public String getActivePolls() {
        return "Get active polls API - logic pending";
    }

    // Cast vote
    @PostMapping("/poll/{pollId}/vote/{candidateId}")
    public String castVote(@PathVariable Long pollId, @PathVariable Long candidateId) {
        return "Cast vote API - logic pending for pollId: " + pollId + ", candidateId: " + candidateId;
    }
}
