package com.onixbyte.helix.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.onixbyte.helix.domain.entity.User;
import com.onixbyte.helix.properties.TokenProperties;
import com.onixbyte.helix.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * A client class responsible for generating JSON Web Tokens (JWT) for user authentication and
 * authorisation purposes.
 * <p>
 * It uses the {@link com.auth0.jwt.JWT} library to create and sign tokens based on user details and
 * configured token properties.
 *
 * @author zihluwang
 */
@Component
public class TokenClient {

    private final Algorithm algorithm;
    private final TokenProperties tokenProperties;

    /**
     * Constructs a new TokenClient with the necessary algorithm and token properties.
     *
     * @param algorithm       the signing algorithm used to secure the JWT
     * @param tokenProperties the configuration properties for the token, such as issuer and
     *                        validity period
     */
    @Autowired
    public TokenClient(Algorithm algorithm, TokenProperties tokenProperties) {
        this.algorithm = algorithm;
        this.tokenProperties = tokenProperties;
    }

    /**
     * Generate a JSON Web Token to the current user.
     *
     * @param user the current user for whom the token is being generated
     * @return a JWT string
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