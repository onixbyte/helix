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
 * @author zihluwang
 * @since 1.0.0
 * @see ConfigurationProperties
 * @see <a href="https://tools.ietf.org/html/rfc7519">RFC 7519 - JSON Web Token (JWT)</a>
 * @see <a href="https://jwt.io/">JWT.io - JSON Web Tokens Introduction</a>
 */
@ConfigurationProperties(prefix = "app.jwt")
public class TokenProperties {

    /**
     * The issuer identifier for JWT tokens.
     * <p>
     * This claim identifies the principal that issued the JWT. The issuer value
     * is typically a string containing a StringOrURI value, commonly the application
     * name or a URL identifying the token issuer. This value is included in the
     * JWT payload and is validated during token verification to ensure the token
     * was issued by a trusted source.
     * <p>
     * Example: {@code "helix-server"} or {@code "https://api.example.com"}
     */
    private String issuer;

    /**
     * The secret key used for signing and verifying JWT tokens.
     * <p>
     * This symmetric key is used with HMAC-based algorithms (such as HS256) to
     * create and validate the digital signature of JWT tokens. The secret must be
     * kept confidential and should be sufficiently long and random to ensure
     * cryptographic security.
     * <p>
     * <strong>Security Requirements:</strong>
     * <ul>
     * <li>Minimum length of 256 bits (32 characters) for HS256 algorithm</li>
     * <li>Should be cryptographically random and unique per environment</li>
     * <li>Must be stored securely and never exposed in logs or version control</li>
     * </ul>
     */
    private String secret;

    /**
     * The validity duration for JWT tokens.
     * <p>
     * This property defines how long a JWT token remains valid after issuance.
     * After this duration expires, the token will be considered invalid and
     * authentication will fail. The duration should balance security (shorter
     * is more secure) with user experience (longer reduces re-authentication frequency).
     * <p>
     * The value is specified using ISO-8601 duration format, such as:
     * <ul>
     * <li>{@code PT15M} - 15 minutes</li>
     * <li>{@code PT1H} - 1 hour</li>
     * <li>{@code PT24H} - 24 hours</li>
     * <li>{@code P1D} - 1 day</li>
     * </ul>
     */
    private Duration validTime;

    /**
     * Constructs a new {@code TokenProperties} instance.
     * <p>
     * This default constructor is used by Spring Boot's configuration property
     * binding mechanism to create and populate the properties bean.
     */
    public TokenProperties() {
    }

    /**
     * Retrieves the JWT token issuer identifier.
     *
     * @return the issuer string, or {@code null} if not configured
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Sets the JWT token issuer identifier.
     *
     * @param issuer the issuer string to set, typically an application name or URL
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * Retrieves the JWT signing secret key.
     *
     * @return the secret key string, or {@code null} if not configured
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Sets the JWT signing secret key.
     *
     * @param secret the secret key to set, must be at least 256 bits for security
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * Retrieves the JWT token validity duration.
     *
     * @return the validity duration, or {@code null} if not configured
     */
    public Duration getValidTime() {
        return validTime;
    }

    /**
     * Sets the JWT token validity duration.
     *
     * @param validTime the validity duration to set, using ISO-8601 duration format
     */
    public void setValidTime(Duration validTime) {
        this.validTime = validTime;
    }
}
