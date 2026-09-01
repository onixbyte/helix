package com.onixbyte.helix.config;

import com.onixbyte.helix.properties.ApplicationProperties;
import com.onixbyte.helix.properties.AuthenticationProperties;
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
 * @see MsalProperties
 * @see EnableConfigurationProperties
 * @since 1.0.0
 */
@Configuration
@EnableConfigurationProperties({
        MsalProperties.class,
        AuthenticationProperties.class,
        ApplicationProperties.class
})
public class AuthenticationConfig {
}
