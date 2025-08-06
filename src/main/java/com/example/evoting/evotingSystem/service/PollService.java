package com.example.evoting.evotingSystem.service;

import com.example.evoting.evotingSystem.model.Poll;
import java.util.List;

public interface PollService {

    // Create new poll
    Poll createPoll(Poll poll);
    List<Poll> getAllPolls();

    // Change poll status (activate/deactivate)
    Poll changePollStatus(Long pollId, boolean status);

    // Get active polls
    List<Poll> getActivePolls();

    // Get poll by ID
    Poll getPollById(Long pollId);
}
