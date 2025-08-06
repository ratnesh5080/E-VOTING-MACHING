package com.example.evoting.evotingSystem.repository;

import com.example.evoting.evotingSystem.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    // Find active polls
    List<Poll> findByActiveTrue();
}
