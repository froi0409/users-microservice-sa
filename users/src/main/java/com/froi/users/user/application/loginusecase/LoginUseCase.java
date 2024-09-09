package com.froi.users.user.application.loginusecase;

import com.froi.users.user.application.exceptions.InvalidCredentialsException;
import com.froi.users.common.UseCase;
import com.froi.users.user.domain.User;
import com.froi.users.user.infrastructure.inputports.LoginInputPort;
import com.froi.users.user.infrastructure.outputadapters.db.UserDbOutputAdapter;
import com.froi.users.user.infrastructure.outputports.JwtOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@UseCase
public class LoginUseCase implements LoginInputPort {

    private JwtOutputPort jwtOutputPort;
    private UserDbOutputAdapter userDbOutputAdapter;
    private AuthenticationManager authenticationManager;

    @Autowired
    public LoginUseCase(JwtOutputPort jwtOutputPort, UserDbOutputAdapter userDbOutputAdapter, AuthenticationManager authenticationManager) {
        this.jwtOutputPort = jwtOutputPort;
        this.userDbOutputAdapter = userDbOutputAdapter;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(String username, String password) throws InvalidCredentialsException {
        User user = userDbOutputAdapter
                .findByUsername(username)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password"));

        if (!user.checkPassword(password)) {
            throw new InvalidCredentialsException("Invalid username or password");
        }

        UsernamePasswordAuthenticationToken authData
                = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authData);
            if (authentication.isAuthenticated()) {
                return jwtOutputPort.generateToken(username, user.getRole());
            }
        } catch (AuthenticationException ex) {
            System.err.println("Error authenticating user: " + ex.getMessage());
        }

        return "";
    }



}
