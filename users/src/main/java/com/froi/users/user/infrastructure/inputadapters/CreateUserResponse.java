package com.froi.users.user.infrastructure.inputadapters;

import com.froi.users.user.domain.User;
import lombok.Value;

@Value
public class CreateUserResponse {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String birthDate;

    public static CreateUserResponse fromDomain(User user) {
        return new CreateUserResponse(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthDate().toString()
        );
    }
}
