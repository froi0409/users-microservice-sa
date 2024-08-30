package com.froi.users.user.domain;

import com.froi.users.user.common.DomainEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@DomainEntity
public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private LocalDateTime tokenExpiration;
    private UserRoleEnum role;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void encodePassword() {
        if (password != null && !password.isEmpty()) {
            password = passwordEncoder.encode(password);
        }
    }

}
