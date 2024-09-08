package com.froi.users.user.infrastructure.outputadapters.jwt;

import com.froi.users.user.domain.UserRoleEnum;
import com.froi.users.user.infrastructure.outputports.JwtOutputPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Service
public class JwtOutputAdapter implements JwtOutputPort {

    private final long EXPIRATION_TIME = 86400000;
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Override
    public String generateToken(String username, UserRoleEnum role) {
        return Jwts.builder()
                .claim("role", role.toString())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    @Override
    public String getUsername(String token) {
        return null;
    }

    @Override
    public boolean isValid(String token) {
        return false;
    }

    @Override
    public void updateTokenExpiration(String username) {

    }

    @Override
    public boolean isTokenExpired(String username) {
        return false;
    }

}
