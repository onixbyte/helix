package com.onixbyte.helix.config;

import com.onixbyte.identitygenerator.IdentityGenerator;
import com.onixbyte.identitygenerator.impl.SequentialUuidGenerator;
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

    /**
     * Creates a Snowflake-based identity generator for user IDs.
     * <p>
     * This method configures a {@link SnowflakeIdentityGenerator} with machine ID and data centre
     * ID both set to 0. The generator produces unique 64-bit Long identifiers suitable for user
     * entity primary keys in distributed environments.
     * <p>
     * The generated IDs are:
     * <ul>
     *   <li>Globally unique across all instances</li>
     *   <li>Time-ordered (newer IDs have higher values)</li>
     *   <li>Highly performant with minimal coordination overhead</li>
     * </ul>
     *
     * @return a configured {@link SnowflakeIdentityGenerator} instance for generating user IDs
     * @see SnowflakeIdentityGenerator
     * @see IdentityGenerator
     */
    @Bean
    public IdentityGenerator<Long> userIdentityGenerator() {
        return new SnowflakeIdentityGenerator(0x0, 0x0);
    }
}
