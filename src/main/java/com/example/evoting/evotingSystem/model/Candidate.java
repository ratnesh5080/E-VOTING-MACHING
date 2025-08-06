package com.example.evoting.evotingSystem.model;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    public Candidate() {}

    public Candidate(String name, String position, Poll poll) {
        this.name = name;
        this.position = position;
        this.poll = poll;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public Poll getPoll() { return poll; }
    public void setPoll(Poll poll) { this.poll = poll; }
}
