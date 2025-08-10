package com.onixbyte.onixboot.config;

import com.onixbyte.onixboot.properties.WeComProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({WeComProperties.class})
public class AuthenticationConfiguration {
}
