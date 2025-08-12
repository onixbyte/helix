package com.onixbyte.onixboot.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.onixbyte.onixboot.model.User;
import com.onixbyte.onixboot.properties.JsonWebTokenProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class JsonWebTokenService {

    private final Algorithm jwtAlgorithm;
    private final JsonWebTokenProperties jsonWebTokenProperties;

    public JsonWebTokenService(
            Algorithm jwtAlgorithm,
            JsonWebTokenProperties jsonWebTokenProperties
    ) {
        this.jwtAlgorithm = jwtAlgorithm;
        this.jsonWebTokenProperties = jsonWebTokenProperties;
    }

    public String createToken(User user) {
        var issuedAt = LocalDateTime.now();
        var expiresAt = issuedAt.plus(jsonWebTokenProperties.getValidTime());

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
                .withIssuer(jsonWebTokenProperties.getIssuer())
                .withIssuedAt(issuedAtInstant)
                .withNotBefore(issuedAtInstant)
                .withExpiresAt(expiresAtInstant)
                .sign(jwtAlgorithm);
    }
}
