package com.froi.users.user.application.exceptions;

public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
