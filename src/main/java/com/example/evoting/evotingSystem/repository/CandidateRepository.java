package com.example.evoting.evotingSystem.repository;

import com.example.evoting.evotingSystem.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    // Get all candidates for a specific poll
    List<Candidate> findByPollId(Long pollId);
}
