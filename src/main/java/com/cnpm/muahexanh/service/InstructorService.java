package com.cnpm.muahexanh.service;

import java.util.List;
import java.util.Optional;

import com.cnpm.muahexanh.model.entity.Instructor;
import com.cnpm.muahexanh.model.request.InstructorRequest;
import com.cnpm.muahexanh.model.request.InstructorUpdateRequest;
import com.cnpm.muahexanh.model.request.UserAddressRequest;
import com.cnpm.muahexanh.model.response.InstructorGetCourseResponse;
import com.cnpm.muahexanh.model.response.InstructorResponse;
import com.cnpm.muahexanh.model.response.UserAddressResponse;

public interface InstructorService {
    List<InstructorResponse> findAll();

    List<InstructorResponse> findByName(String name);

    Optional<InstructorResponse> findById(Long instructorId);

    InstructorResponse createInstructor(InstructorRequest instructorRequest);

    InstructorResponse updateInstructor(InstructorUpdateRequest instructorUpdateRequest, Long id);

    InstructorResponse updateInstructorPassword(Long id, String password);

    InstructorResponse recoverInstructorPassword(Long id, String password);

    void deleteInstructor(Long instructorId);

    // List<InstructorGetCourseResponse> getCoursesByInstructor(Long instructorId);

    UserAddressResponse updateInstructorAddress(Long userId, UserAddressRequest addressRequest);
}
