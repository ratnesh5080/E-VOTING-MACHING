package com.example.evoting.evotingSystem.serviceImpl;

import com.example.evoting.evotingSystem.model.Candidate;
import com.example.evoting.evotingSystem.model.Poll;
import com.example.evoting.evotingSystem.model.User;
import com.example.evoting.evotingSystem.model.Vote;
import com.example.evoting.evotingSystem.repository.CandidateRepository;
import com.example.evoting.evotingSystem.repository.PollRepository;
import com.example.evoting.evotingSystem.repository.UserRepository;
import com.example.evoting.evotingSystem.repository.VoteRepository;
import com.example.evoting.evotingSystem.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PollRepository pollRepository;

   /* @Override
    public Vote castVote(Long voterId, Long candidateId, Long pollId) {
        if (voteRepository.existsByVoterIdAndPollId(voterId, pollId)) {
            throw new RuntimeException("User already voted in this poll!");
        }

        User voter = userRepository.findById(voterId).orElseThrow(() -> new RuntimeException("Voter not found!"));
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(() -> new RuntimeException("Candidate not found!"));
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new RuntimeException("Poll not found!"));

        Vote vote = new Vote(voter, candidate, poll, LocalDateTime.now());
        return voteRepository.save(vote);
    }*/
    @Override
    public String castVote(Long pollId, String voterEmail, String candidateName) {
        if (voteRepository.existsByPollIdAndVoterEmail(pollId, voterEmail)) {
            return "You have already voted in this poll.";
        }
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));

        Vote vote = new Vote();
        vote.setPoll(poll);
        vote.setVoterEmail(voterEmail);
        vote.setCandidateName(candidateName);

        voteRepository.save(vote);
        return "Vote cast successfully!";
    }

    @Override
    public boolean hasVoted(Long voterId, Long pollId) {
        return voteRepository.existsByVoterIdAndPollId(voterId, pollId);
    }

    @Override
    public List<Object[]> getPollResults(Long pollId) {
        List<Candidate> candidates = candidateRepository.findByPollId(pollId);
        List<Object[]> results = new ArrayList<>();

        for (Candidate candidate : candidates) {
            Long count = voteRepository.countByCandidateId(candidate.getId());
            results.add(new Object[]{candidate.getName(), count});
        }
        return results;
    }

	@Override
	public Vote castVote(Long voterId, Long candidateId, Long pollId) {
		// TODO Auto-generated method stub
		return null;
	}
}
