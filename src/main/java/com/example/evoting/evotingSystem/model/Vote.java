package com.example.evoting.evotingSystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voter_id")
    private User voter;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;
    private String voterEmail;
    private String candidateName;

    private LocalDateTime voteTime;

    public Vote() {}

    public Vote(User voter, Candidate candidate, Poll poll, LocalDateTime voteTime) {
        this.voter = voter;
        this.candidate = candidate;
        this.poll = poll;
        this.voteTime = voteTime;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getVoter() { return voter; }
    public void setVoter(User voter) { this.voter = voter; }

    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }

    public Poll getPoll() { return poll; }
    public void setPoll(Poll poll) { this.poll = poll; }

    public LocalDateTime getVoteTime() { return voteTime; }
    public void setVoteTime(LocalDateTime voteTime) { this.voteTime = voteTime; }

	public String getVoterEmail() {
		return voterEmail;
	}

	public void setVoterEmail(String voterEmail) {
		this.voterEmail = voterEmail;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
}
