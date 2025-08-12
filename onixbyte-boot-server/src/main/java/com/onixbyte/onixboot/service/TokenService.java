package com.onixbyte.onixboot.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.onixbyte.onixboot.model.User;
import com.onixbyte.onixboot.properties.TokenProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TokenService {

    private final Algorithm jwtAlgorithm;
    private final TokenProperties tokenProperties;

    public TokenService(
            Algorithm jwtAlgorithm,
            TokenProperties tokenProperties
    ) {
        this.jwtAlgorithm = jwtAlgorithm;
        this.tokenProperties = tokenProperties;
    }

    public String createToken(User user) {
        var issuedAt = LocalDateTime.now();
        var expiresAt = issuedAt.plus(tokenProperties.getValidTime());

        if (expiresAt.isBefore(issuedAt)) {
            throw new IllegalStateException("Issue time must before expire time.");
        }

        var issuedAtInstant = issuedAt
                .atZone(ZoneId.systemDefault())
                .toInstant();

        var expiresAtInstant = expiresAt
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return JWT.create()
                .withAudience("OnixByte Boot Admin")
                .withSubject(String.valueOf(user.getId()))
                .withIssuer(tokenProperties.getIssuer())
                .withIssuedAt(issuedAtInstant)
                .withNotBefore(issuedAtInstant)
                .withExpiresAt(expiresAtInstant)
                .sign(jwtAlgorithm);
    }
}
