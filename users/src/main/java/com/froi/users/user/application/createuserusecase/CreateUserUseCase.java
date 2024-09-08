package com.froi.users.user.application.createuserusecase;

import com.froi.users.common.UseCase;
import com.froi.users.common.exceptions.application.DuplicatedEntityException;
import com.froi.users.user.domain.User;
import com.froi.users.user.infrastructure.inputports.CreateUserInputPort;
import com.froi.users.user.infrastructure.outputadapters.db.UserDbOutputAdapter;
import org.springframework.beans.factory.annotation.Autowired;

@UseCase
public class CreateUserUseCase implements CreateUserInputPort {

    private UserDbOutputAdapter userDbOutputAdapter;

    @Autowired
    public CreateUserUseCase(UserDbOutputAdapter userDbOutputAdapter) {
        this.userDbOutputAdapter = userDbOutputAdapter;
    }

    @Override
    public User createNormalUser(CreateUserRequest createUserRequest) throws DuplicatedEntityException {
        verifyCredentials(createUserRequest.getUsername(), createUserRequest.getEmail());

        User user = createUserRequest.toDomain();
        user.encodePassword();
        return userDbOutputAdapter.create(user);
    }

    @Override
    public User createEmployeeUser(CreateEmployeeUserRequest createEmployeeUserRequest) throws DuplicatedEntityException {
        verifyCredentials(createEmployeeUserRequest.getUsername(), createEmployeeUserRequest.getEmail());

        User user = createEmployeeUserRequest.toDomain();
        user.encodePassword();
        return userDbOutputAdapter.create(user);
    }

    public void verifyCredentials(String username, String email) throws DuplicatedEntityException {
        // Verify if username already exists
        userDbOutputAdapter.findByUsername(username)
                .orElseThrow(() -> new DuplicatedEntityException(String.format("Username %s already exists", username)));

        // Verify if email already exists
        userDbOutputAdapter.findByEmail(email)
                .orElseThrow(() -> new DuplicatedEntityException(String.format("Email %s already exists", email)));

    }
}
