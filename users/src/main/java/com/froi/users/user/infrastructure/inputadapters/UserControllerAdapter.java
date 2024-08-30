package com.froi.users.user.infrastructure.inputadapters;

import com.froi.users.user.application.createuserusecase.CreateEmployeeUserRequest;
import com.froi.users.user.application.createuserusecase.CreateUserRequest;
import com.froi.users.user.common.WebAdapter;
import com.froi.users.user.common.exceptions.application.DuplicatedEntityException;
import com.froi.users.user.domain.User;
import com.froi.users.user.infrastructure.inputports.CreateUserInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@WebAdapter
public class UserControllerAdapter {
    private CreateUserInputPort createUserInputPort;

    @Autowired
    public UserControllerAdapter(CreateUserInputPort createUserInputPort) {
        this.createUserInputPort = createUserInputPort;
    }

    @PostMapping("/normal")
    public ResponseEntity<CreateUserResponse> createNormalUser(CreateUserRequest createUserRequest) throws DuplicatedEntityException {
        User user = createUserInputPort.createNormalUser(createUserRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateUserResponse.fromDomain(user));
    }

    @PostMapping("/employee")
    public ResponseEntity<CreateUserResponse> createEmployeeUser(CreateEmployeeUserRequest createUserRequest) throws DuplicatedEntityException {
        User user = createUserInputPort.createEmployeeUser(createUserRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateUserResponse.fromDomain(user));
    }

}
