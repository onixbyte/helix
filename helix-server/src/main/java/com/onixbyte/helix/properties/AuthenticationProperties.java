package com.onixbyte.helix.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "app.auth")
public record AuthenticationProperties(
        @DefaultValue("true") Boolean sslEnabled,
        @DefaultValue("true") Boolean secureCookieEnabled
) {
}
