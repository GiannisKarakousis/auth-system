package com.example.auth_system.exception;

public class UserAlreadyExistAuthenticationException extends ArithmeticException {

    public UserAlreadyExistAuthenticationException(final String msg) {
        super(msg);
    }
}
