package com.froi.users.user.infrastructure.inputadapters;

import com.froi.users.user.application.createuserusecase.CreateEmployeeUserRequest;
import com.froi.users.user.application.createuserusecase.CreateUserRequest;
import com.froi.users.common.WebAdapter;
import com.froi.users.common.exceptions.application.DuplicatedEntityException;
import com.froi.users.user.domain.User;
import com.froi.users.user.infrastructure.inputports.CreateUserInputPort;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/v1/users")
@WebAdapter
@SecurityRequirement(name = "bearerAuth")
public class UserControllerAdapter {
    private CreateUserInputPort createUserInputPort;

    @Autowired
    public UserControllerAdapter(CreateUserInputPort createUserInputPort) {
        this.createUserInputPort = createUserInputPort;
    }

    @PostMapping("/normal")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CreateUserResponse> createNormalUser(@RequestBody CreateUserRequest createUserRequest) throws DuplicatedEntityException {
        User user = createUserInputPort.createNormalUser(createUserRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateUserResponse.fromDomain(user));
    }

    @PostMapping("/employee")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CreateUserResponse> createEmployeeUser(@RequestBody CreateEmployeeUserRequest createUserRequest) throws DuplicatedEntityException {
        User user = createUserInputPort.createEmployeeUser(createUserRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateUserResponse.fromDomain(user));
    }

}
