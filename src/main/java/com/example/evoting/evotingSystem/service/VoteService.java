package com.example.evoting.evotingSystem.service;

import com.example.evoting.evotingSystem.model.Vote;
import java.util.List;

public interface VoteService {

    // Cast a vote
    Vote castVote(Long voterId, Long candidateId, Long pollId);
    String castVote(Long pollId, String voterEmail, String candidateName);

    // Check if voter already voted in poll
    boolean hasVoted(Long voterId, Long pollId);

    // Get results for a poll (candidate-wise count)
    List<Object[]> getPollResults(Long pollId);
}
