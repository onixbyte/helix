package com.onixbyte.helix.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "app.captcha")
public record CaptchaProperties(
        @DefaultValue("6") int length
) {
}
