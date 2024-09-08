package com.froi.users.user.infrastructure.inputports;

import com.froi.users.user.application.exceptions.InvalidCredentialsException;

public interface LoginInputPort {
    String login(String username, String password) throws InvalidCredentialsException;
}
