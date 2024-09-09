package com.froi.users.user.application.createuserusecase;

import com.froi.users.user.domain.User;
import lombok.Value;

import java.time.LocalDate;

@Value
public class CreateUserRequest {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String birthDate;

    public User toDomain() {
        LocalDate birthDate = LocalDate.parse(this.birthDate);

        return User.builder()
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .birthDate(birthDate)
                .build();
    }
}
