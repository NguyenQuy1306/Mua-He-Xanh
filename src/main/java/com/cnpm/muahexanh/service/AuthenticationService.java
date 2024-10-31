package com.cnpm.muahexanh.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.cnpm.muahexanh.model.request.AuthenticationRequest;
import com.cnpm.muahexanh.model.request.RegisterRequest;
import com.cnpm.muahexanh.model.response.AuthenticationResponse;
import com.cnpm.muahexanh.model.response.LoginResponse;
import com.cnpm.muahexanh.model.response.UserResponse;

public interface AuthenticationService {

    UserResponse register(RegisterRequest request);

    LoginResponse authenticate(AuthenticationRequest request, HttpServletResponse response);

    void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException;
}
