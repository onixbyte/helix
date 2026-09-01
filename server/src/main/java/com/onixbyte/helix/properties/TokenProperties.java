package com.onixbyte.helix.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * Configuration properties for JSON Web Token (JWT) generation and validation.
 * <p>
 * This class provides configuration binding for JWT-related settings used throughout
 * the Helix application for user authentication and authorisation. It encapsulates
 * the essential parameters required for creating, signing, and validating JWT tokens
 * in a secure and consistent manner.
 * <p>
 * <strong>JWT Overview:</strong> JSON Web Tokens are a compact, URL-safe means of
 * representing claims to be transferred between two parties. In the Helix application,
 * JWTs are used to maintain user session state and carry authentication information
 * across API requests without requiring server-side session storage.
 * <p>
 * <strong>Configuration Example:</strong>
 * <pre>{@code
 * app:
 *   jwt:
 *     issuer: "helix-server"
 *     secret: "your-256-bit-secret-key-here"
 *     valid-time: PT24H  # 24 hours
 * }</pre>
 * <p>
 * <strong>Security Considerations:</strong>
 * <ul>
 * <li><strong>Secret Key:</strong> The secret must be kept confidential and should be
 *     at least 256 bits (32 characters) long for HS256 algorithm security.</li>
 * <li><strong>Token Expiry:</strong> Set appropriate validity periods to balance
 *     user experience with security requirements.</li>
 * <li><strong>Issuer Validation:</strong> The issuer claim helps prevent token
 *     misuse across different applications or environments.</li>
 * </ul>
 * <p>
 * All properties are prefixed with {@code app.jwt} and are automatically bound
 * by Spring Boot's configuration property mechanism.
 *
 * @param issuer    name of the issuer
 * @param secret    secret to sign a token
 * @param validTime validity duration for JWT tokens
 * @author zihluwang
 * @see ConfigurationProperties
 * @see <a href="https://tools.ietf.org/html/rfc7519">RFC 7519 - JSON Web Token (JWT)</a>
 * @see <a href="https://jwt.io/">JWT.io - JSON Web Tokens Introduction</a>
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.jwt")
public record TokenProperties(
        String issuer,
        String secret,
        Duration validTime
) {
}
