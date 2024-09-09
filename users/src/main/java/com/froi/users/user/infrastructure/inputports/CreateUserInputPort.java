package com.froi.users.user.infrastructure.inputports;

import com.froi.users.user.application.createuserusecase.CreateEmployeeUserRequest;
import com.froi.users.user.application.createuserusecase.CreateUserRequest;
import com.froi.users.common.exceptions.application.DuplicatedEntityException;
import com.froi.users.user.domain.User;

public interface CreateUserInputPort {
    User createNormalUser(CreateUserRequest createUserRequest) throws DuplicatedEntityException;

    User createEmployeeUser(CreateEmployeeUserRequest createEmployeeUserRequest) throws DuplicatedEntityException;
}
