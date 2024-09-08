package com.froi.users.user.infrastructure.outputadapters.db;

import com.froi.users.user.domain.User;
import com.froi.users.user.domain.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_account", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDbEntity {
    @Id
    @Column
    private String username;

    @Column
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column
    private String role;

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;

    @Column
    private String email;

    public User toDomain() {
        return User.builder()
                .username(username)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .birthDate(birthDate)
                .role(UserRoleEnum.valueOf(role))
                .tokenExpiration(tokenExpiration)
                .build();
    }

    public static UserDbEntity fromDomain(User user) {
        return new UserDbEntity(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getRole().toString(),
                user.getTokenExpiration(),
                user.getEmail());
    }

}
