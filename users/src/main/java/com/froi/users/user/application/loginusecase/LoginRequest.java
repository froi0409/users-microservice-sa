package com.froi.users.user.application.loginusecase;

import lombok.Value;

@Value
public class LoginRequest {
    private final String username;
    private final String password;
}
