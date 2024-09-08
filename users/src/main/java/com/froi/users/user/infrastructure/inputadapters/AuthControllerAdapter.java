package com.froi.users.user.infrastructure.inputadapters;

import com.froi.users.user.application.exceptions.InvalidCredentialsException;
import com.froi.users.user.application.loginusecase.LoginRequest;
import com.froi.users.user.application.loginusecase.LoginUseCase;
import com.froi.users.user.infrastructure.inputports.LoginInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/v1/auth")
public class AuthControllerAdapter {

    private LoginInputPort loginInputPort;

    @Autowired
    public AuthControllerAdapter(LoginInputPort loginInputPort) {
        this.loginInputPort = loginInputPort;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) throws InvalidCredentialsException {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        return ResponseEntity.ok(loginInputPort.login(username, password));
    }

}
