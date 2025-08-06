package com.example.evoting.evotingSystem.repository;

import com.example.evoting.evotingSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by email (for login & OTP)
    Optional<User> findByEmail(String email);

    // Check if email exists
    boolean existsByEmail(String email);
}
