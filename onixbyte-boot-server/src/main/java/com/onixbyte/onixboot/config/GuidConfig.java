package com.onixbyte.onixboot.config;

import com.onixbyte.identitygenerator.IdentityGenerator;
import com.onixbyte.identitygenerator.impl.SnowflakeIdentityGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuidConfig {

    /**
     * User ID generator.
     *
     * @return a {@link SnowflakeIdentityGenerator} instance for generating user ID
     */
    @Bean
    public IdentityGenerator<Long> userIdentityGenerator() {
        return new SnowflakeIdentityGenerator(0x0, 0x0);
    }
}
