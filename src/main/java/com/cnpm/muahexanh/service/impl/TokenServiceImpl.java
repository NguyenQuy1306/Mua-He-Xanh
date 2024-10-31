package com.cnpm.muahexanh.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnpm.muahexanh.model.entity.Token;
import com.cnpm.muahexanh.repository.TokenRepository;
import com.cnpm.muahexanh.service.TokenService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public List<Token> findAllValidTokenByUser(Long id) {
        return List.of();
    }

    @Override
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
