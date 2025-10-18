package com.onixbyte.helix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.properties.TokenProperties;
import com.onixbyte.helix.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenService {

    private final Algorithm algorithm;
    private final TokenProperties tokenProperties;

    public TokenService(Algorithm algorithm, TokenProperties tokenProperties) {
        this.algorithm = algorithm;
        this.tokenProperties = tokenProperties;
    }

    /**
     * Generate a JSON Web Token to the current user.
     *
     * @param user current user
     * @return a JWT starts with {@code Bearer} prefix
     */
    public String generateToken(User user) {
        var issuedAt = LocalDateTime.now();
        var expiresAt = issuedAt.plus(tokenProperties.validTime());

        return JWT.create()
                .withSubject(user.getUsername())
                .withAudience("Helix Web")
                .withIssuer(tokenProperties.issuer())
                .withIssuedAt(DateTimeUtil.asInstant(issuedAt))
                .withExpiresAt(DateTimeUtil.asInstant(expiresAt))
                .sign(algorithm);
    }
}
