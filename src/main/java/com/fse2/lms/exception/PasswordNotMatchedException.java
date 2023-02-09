package com.fse2.lms.exception;

public class PasswordNotMatchedException extends RuntimeException {

    private static final long serialVersionUID = 2L;

    public PasswordNotMatchedException(String message) {
        super(message);
    }
}
