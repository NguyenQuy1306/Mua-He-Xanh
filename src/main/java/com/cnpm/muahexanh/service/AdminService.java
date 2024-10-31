package com.cnpm.muahexanh.service;

import java.util.Optional;

import com.cnpm.muahexanh.model.entity.Admin;
import com.cnpm.muahexanh.model.response.AdminResponse;

public interface AdminService {
    Boolean save(Admin admin);

    Optional<AdminResponse> findById(Long id);

    Optional<AdminResponse> findByEmail(String email);
}
