package com.cnpm.muahexanh.service;

import com.cnpm.muahexanh.model.request.RegisterRequest;
import com.cnpm.muahexanh.model.response.UserResponse;

public interface UserService {
    UserResponse createUser(RegisterRequest registerRequest);

    UserResponse updateUser(RegisterRequest registerRequest);

    UserResponse deleteUser(String userId);

    UserResponse getUser(String userId);

    UserResponse getUserByEmail(String email);

    UserResponse getUserByUsername(String username);

    UserResponse activateUser(String email);
}
