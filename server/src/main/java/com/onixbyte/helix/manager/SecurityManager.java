package com.onixbyte.helix.manager;

import com.onixbyte.helix.properties.TokenProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class SecurityManager {

    private final TokenProperties tokenProperties;

    public SecurityManager(TokenProperties tokenProperties) {
        this.tokenProperties = tokenProperties;
    }

    public Duration getTokenValidDuration() {
        return tokenProperties.validTime();
    }
}
