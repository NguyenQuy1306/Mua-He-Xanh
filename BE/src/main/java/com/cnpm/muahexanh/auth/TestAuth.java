package com.cnpm.muahexanh.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cnpm.muahexanh.model.entity.User;
import com.cnpm.muahexanh.repository.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/test")
public class TestAuth {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }
}
