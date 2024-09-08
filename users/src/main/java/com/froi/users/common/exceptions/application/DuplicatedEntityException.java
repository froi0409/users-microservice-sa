package com.froi.users.common.exceptions.application;

public class DuplicatedEntityException extends Exception{
    public DuplicatedEntityException() {
    }

    public DuplicatedEntityException(String message) {
        super(message);
    }
}
