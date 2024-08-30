package com.froi.users.user.common.exceptions.application;

public class DuplicatedEntityException extends Exception{
    public DuplicatedEntityException() {
    }

    public DuplicatedEntityException(String message) {
        super(message);
    }
}
