package com.example.evoting.evotingSystem.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    // Roles: ADMIN, VOTER, CANDIDATE
    private String role;

    private boolean verified = false;

    // For candidates only (optional)
    private String position;

    @OneToMany(mappedBy = "voter", cascade = CascadeType.ALL)
    private List<Vote> votes;

    public User() {}

    public User(String name, String email, String password, String role, String position) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.position = position;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public List<Vote> getVotes() { return votes; }
    public void setVotes(List<Vote> votes) { this.votes = votes; }
}
