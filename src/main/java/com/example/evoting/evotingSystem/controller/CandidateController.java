package com.example.evoting.evotingSystem.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
public class CandidateController {

    // View results for candidate's position
    @GetMapping("/poll/{pollId}/results")
    public String getCandidateResults(@PathVariable Long pollId) {
        return "Candidate result API - logic pending for pollId: " + pollId;
    }
}
