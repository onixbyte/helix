package com.onixbyte.helix.config;

import com.onixbyte.identitygenerator.IdentityGenerator;
import com.onixbyte.identitygenerator.impl.SnowflakeIdentityGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for GUID (Globally Unique Identifier) generation components.
 * <p>
 * This configuration class provides beans for generating unique identifiers throughout the
 * Helix application. It utilises the Snowflake algorithm implementation to ensure globally unique,
 * time-ordered identifiers that are suitable for distributed systems.
 * <p>
 * The Snowflake algorithm generates 64-bit identifiers composed of:
 * <ul>
 *   <li>Timestamp (41 bits) - milliseconds since epoch</li>
 *   <li>Machine ID (10 bits) - identifies the generating machine</li>
 *   <li>Sequence number (12 bits) - counter for same millisecond</li>
 * </ul>
 *
 * @author zihluwang
 * @since 1.0.0
 * @see IdentityGenerator
 * @see SnowflakeIdentityGenerator
 */
@Configuration
public class GuidConfig {

    @Bean
    public IdentityGenerator<Long> userIdentityGenerator() {
        return new SnowflakeIdentityGenerator(0x0, 0x0);
    }
}
