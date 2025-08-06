package com.example.evoting.evotingSystem.repository;

import com.example.evoting.evotingSystem.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    // Check if user already voted in a poll
    boolean existsByVoterIdAndPollId(Long voterId, Long pollId);

    // Get votes for candidate
    Long countByCandidateId(Long candidateId);

    // Get all votes for a poll
    List<Vote> findByPollId(Long pollId);
    boolean existsByPollIdAndVoterEmail(Long pollId, String voterEmail);
}
