package com.froi.users.user.infrastructure.outputadapters.jwt;

import com.froi.users.user.domain.UserRoleEnum;
import com.froi.users.user.infrastructure.outputports.JwtOutputPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Service
public class JwtOutputAdapter implements JwtOutputPort {

    private final long EXPIRATION_TIME = 86400000;
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Override
    public String generateToken(String username, UserRoleEnum role) {
        return Jwts.builder()
                .claims(Collections.singletonMap("role", role))
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public String getUsername(String token) {
        Claims claims = extractClaims(token);
        return claims.getSubject();
    }

    @Override
    public String getRole(String token) {
        Claims claims = extractClaims(token);
        return claims.get("role", String.class);
    }

    @Override
    public boolean isValid(String token) {
        Claims claims = extractClaims(token);
        Date expirationDate = claims.getExpiration();
        return new Date().before(expirationDate);
    }

    @Override
    public void updateTokenExpiration(String username) {

    }

    @Override
    public boolean isTokenExpired(String username) {
        return false;
    }

    public SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
