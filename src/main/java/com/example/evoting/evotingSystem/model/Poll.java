package com.example.evoting.evotingSystem.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "polls")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean active;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<Candidate> candidates;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL)
    private List<Vote> votes;

    public Poll() {}

    public Poll(String name, LocalDateTime startTime, LocalDateTime endTime, boolean active) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }

    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public List<Candidate> getCandidates() { return candidates; }
    public void setCandidates(List<Candidate> candidates) { this.candidates = candidates; }

    public List<Vote> getVotes() { return votes; }
    public void setVotes(List<Vote> votes) { this.votes = votes; }
}
