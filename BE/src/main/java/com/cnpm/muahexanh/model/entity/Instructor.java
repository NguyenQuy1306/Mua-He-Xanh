package com.cnpm.muahexanh.model.entity;

import com.cnpm.muahexanh.model.entity.UserRole.Role;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
// @AllArgsConstructor
// @NoArgsConstructor
@Entity
@DiscriminatorValue(value = Role.INSTRUCTOR)
@Table(name = "instructors")
// @PrimaryKeyJoinColumn(name="instructorId",referencedColumnName = "userId")
@PrimaryKeyJoinColumn(name = "userId")
public class Instructor extends User {

}
