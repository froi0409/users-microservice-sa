package com.froi.users.user.application.createuserusecase;

import com.froi.users.user.domain.User;
import com.froi.users.user.domain.UserRoleEnum;
import lombok.Value;

import java.time.LocalDate;

@Value
public class CreateEmployeeUserRequest {
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String birthDate;
    private final Integer role;

    public User toDomain() {
        LocalDate birthDate = LocalDate.parse(this.birthDate);
        UserRoleEnum role = UserRoleEnum.values()[this.role];

        return User.builder()
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .birthDate(birthDate)
                .role(role)
                .build();
    }
}
