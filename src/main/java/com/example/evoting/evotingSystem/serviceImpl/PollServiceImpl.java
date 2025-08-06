package com.example.evoting.evotingSystem.serviceImpl;

import com.example.evoting.evotingSystem.model.Poll;
import com.example.evoting.evotingSystem.repository.PollRepository;
import com.example.evoting.evotingSystem.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Override
    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public Poll changePollStatus(Long pollId, boolean status) {
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new RuntimeException("Poll not found!"));
        poll.setActive(status);
        return pollRepository.save(poll);
    }

    @Override
    public List<Poll> getActivePolls() {
        return pollRepository.findByActiveTrue();
    }

    @Override
    public Poll getPollById(Long pollId) {
        return pollRepository.findById(pollId).orElseThrow(() -> new RuntimeException("Poll not found!"));
    }
    @Override
    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }
}
