package com.cnpm.muahexanh.service;

import java.util.Optional;

import com.cnpm.muahexanh.model.entity.User;

public interface VerificationTokenService {
    Optional<String> createVerificationToken(String email);

    Optional<User> validateVerificationToken(String token);

    void revokePreviousTokens(Long userId);
}
