package com.cnpm.muahexanh.service;

import java.util.List;
import java.util.Optional;

import com.cnpm.muahexanh.model.entity.Token;

public interface TokenService {
    List<Token> findAllValidTokenByUser(Long id);

    Optional<Token> findByToken(String token);
}
