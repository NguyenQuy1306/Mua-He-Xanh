package com.cnpm.muahexanh.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
public class UserAddressRequest {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String userAddress;

    private String userCity;

    private String userCountry;

    private Long userPostalCode;
}
