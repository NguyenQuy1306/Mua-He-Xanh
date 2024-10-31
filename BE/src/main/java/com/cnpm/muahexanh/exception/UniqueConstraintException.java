package com.cnpm.muahexanh.exception;

public class UniqueConstraintException extends RuntimeException {
    public UniqueConstraintException() {
    }

    public UniqueConstraintException(String message) {
        super(message);
    }
}
