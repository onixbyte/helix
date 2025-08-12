package com.onixbyte.onixboot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@ConfigurationProperties(prefix = "app.jwt")
public class TokenProperties {

    private String issuer;

    private String secret;

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
