package com.cnpm.muahexanh.exception;

public class InactivatedUserException extends RuntimeException {
    public InactivatedUserException(String message) {
        super(message);
    }
}
