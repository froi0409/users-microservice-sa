package com.froi.users.user.infrastructure.outputports;

import com.froi.users.user.domain.User;

import java.util.Optional;

public interface FindUserOutputPort {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
