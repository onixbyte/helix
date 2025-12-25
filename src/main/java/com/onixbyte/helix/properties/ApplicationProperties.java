package com.onixbyte.helix.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "app.common")
public record ApplicationProperties(
        @DefaultValue("default@helix.onixbyte.dev") String defaultEmail
) {
}
