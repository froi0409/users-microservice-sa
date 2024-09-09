package com.froi.users.user.infrastructure.outputports;


import com.froi.users.user.domain.UserRoleEnum;

public interface JwtOutputPort {
    String generateToken(String username, UserRoleEnum role);

    String getUsername(String token);

    String getRole(String token);

    boolean isValid(String token);

    void updateTokenExpiration(String username);

    public boolean isTokenExpired(String username);
}
