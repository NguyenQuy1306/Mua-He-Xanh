package com.cnpm.muahexanh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cnpm.muahexanh.model.entity.VerificationToken;

import java.util.List;
import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);

    List<VerificationToken> findAllByUser_UserId(Long userId);

}
