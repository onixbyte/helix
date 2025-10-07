package com.onixbyte.helix.config;

import com.onixbyte.helix.properties.MsalProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for authentication-related components and properties.
 * <p>
 * This configuration class is responsible for enabling and managing custom configuration properties
 * related to authentication mechanisms within the Helix application. It specifically enables the
 * {@link MsalProperties} configuration properties to support Microsoft Authentication Library
 * (MSAL) integration.
 * <p>
 * The class serves as a central point for authentication configuration, ensuring that all
 * authentication-related properties are properly loaded and made available to the Spring
 * application context.
 *
 * @author zihluwang
 * @since 1.0.0
 * @see MsalProperties
 * @see EnableConfigurationProperties
 */
@Configuration
@EnableConfigurationProperties({MsalProperties.class})
public class AuthenticationConfig {
}
