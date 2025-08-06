package com.example.evoting.evotingSystem.controller;

import com.example.evoting.evotingSystem.model.Poll;
import com.example.evoting.evotingSystem.service.PollService;
import com.example.evoting.evotingSystem.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private VoteService voteService;

    // Fetch all polls
    @GetMapping("/polls")
 //   public List<Poll> getAllPolls() {
  //      return pollService.getAllPolls();
  //  }
    public ResponseEntity<List<Poll>> getAllPolls() {
        List<Poll> polls = pollService.getAllPolls();
        return ResponseEntity.ok(polls);  // returns JSON automatically
    }

    // Admin: create poll
    @PostMapping("/admin/polls")
    public Poll createPoll(@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }

    // Voter: cast vote
    @PostMapping("/voter/vote/{pollId}")
    public String castVote(@PathVariable Long pollId, @RequestBody Map<String, String> request) {
        String voterEmail = request.get("voterEmail");
        String candidateName = request.get("candidateName");
        return voteService.castVote(pollId, voterEmail, candidateName);
    }
}
