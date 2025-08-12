package com.onixbyte.onixboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * Token properties, used to assist in generating user identity tokens.
 *
 * @author zihluwang
 */
@ConfigurationProperties(prefix = "app.jwt")
public class TokenProperties {

    /**
     * The name of the person or organisation issuing the token.
     */
    private String issuer;

    /**
     * The secret of verifying the authenticity of tokens.
     */
    private String secret;

    /**
     * The validity period of the token.
     */
    private Duration validTime;

    public TokenProperties() {
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Duration getValidTime() {
        return validTime;
    }

    public void setValidTime(Duration validTime) {
        this.validTime = validTime;
    }
}
