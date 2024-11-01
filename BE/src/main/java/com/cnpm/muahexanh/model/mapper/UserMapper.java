package com.cnpm.muahexanh.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.cnpm.muahexanh.model.entity.Admin;
import com.cnpm.muahexanh.model.entity.Instructor;
import com.cnpm.muahexanh.model.entity.Student;
import com.cnpm.muahexanh.model.entity.User;
import com.cnpm.muahexanh.model.response.*;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    // Map Student Entity to StudentResponse
    @Mapping(source = "userId", target = "studentId")
    public abstract StudentResponse toResponse(Student student);

    public abstract List<StudentResponse> toResponseList(List<Student> studentList);

    //
    @Mapping(source = "userId", target = "instructorId")
    public abstract InstructorResponse toInstructorResponse(Instructor instructor);

    public abstract List<InstructorResponse> toInstructorResponseList(List<Instructor> instructors);

    //
    @Mapping(source = "userId", target = "adminId")
    public abstract AdminResponse toAdminResponse(Admin admin);

    //
    @Mapping(target = "userRole", expression = "java(getUserRoleById(user))")
    public abstract UserResponse toUserResponse(User user);

    protected String getUserRoleById(User user) {
        return user.getDiscriminatorValue();
    }

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "userAddress", target = "userAddress")
    @Mapping(source = "userCity", target = "userCity")
    @Mapping(source = "userCountry", target = "userCountry")
    @Mapping(source = "userPostalCode", target = "userPostalCode")
    public abstract UserAddressResponse toUserAddressResponse(Student student);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "userAddress", target = "userAddress")
    @Mapping(source = "userCity", target = "userCity")
    @Mapping(source = "userCountry", target = "userCountry")
    @Mapping(source = "userPostalCode", target = "userPostalCode")
    public abstract UserAddressResponse toUserAddressResponse(Instructor instructor);

}
