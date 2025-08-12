package com.onixbyte.onixboot.config;

import com.onixbyte.onixboot.properties.WecomProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({WecomProperties.class})
public class AuthenticationConfiguration {
}
