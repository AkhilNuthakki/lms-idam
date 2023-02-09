package com.fse2.lms.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 3L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
