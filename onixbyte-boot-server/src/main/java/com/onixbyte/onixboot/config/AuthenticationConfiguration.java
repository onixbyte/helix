package com.onixbyte.onixboot.config;

import com.onixbyte.onixboot.properties.MsalProperties;
import com.onixbyte.onixboot.properties.WecomProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Authentication configuration.
 * <p>
 * Enabled custom configuration properties.
 *
 * @author zihluwang
 */
@Configuration
@EnableConfigurationProperties({WecomProperties.class, MsalProperties.class})
public class AuthenticationConfiguration {
}
