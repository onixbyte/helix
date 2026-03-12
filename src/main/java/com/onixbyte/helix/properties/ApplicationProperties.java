package com.onixbyte.helix.properties;

import com.onixbyte.helix.enumeration.ApplicationMode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "app.common")
public record ApplicationProperties(
        @DefaultValue("default@helix.onixbyte.dev") String defaultEmail,
        @DefaultValue("helix.onixbyte.dev") String externalHost,
        @DefaultValue("prod") ApplicationMode mode
) {
}
