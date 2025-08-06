package com.example.evoting.evotingSystem.repository;

import com.example.evoting.evotingSystem.model.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OtpVerification, Long> {

    // Find OTP by email
    Optional<OtpVerification> findByEmail(String email);

    // Delete OTP after verification
    void deleteByEmail(String email);
}
