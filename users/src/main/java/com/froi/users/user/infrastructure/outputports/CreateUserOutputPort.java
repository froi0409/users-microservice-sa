package com.froi.users.user.infrastructure.outputports;

import com.froi.users.user.domain.User;

public interface CreateUserOutputPort {
    User create(User user);
}
