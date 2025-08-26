package com.onixbyte.helix.config;

import com.onixbyte.helix.properties.MsalProperties;
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
@EnableConfigurationProperties({MsalProperties.class})
public class AuthenticationConfiguration {
}
