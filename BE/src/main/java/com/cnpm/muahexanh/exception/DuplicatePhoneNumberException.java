package com.cnpm.muahexanh.exception;

public class DuplicatePhoneNumberException extends RuntimeException {
    public DuplicatePhoneNumberException(String message) {
        super(message);
    }
}
