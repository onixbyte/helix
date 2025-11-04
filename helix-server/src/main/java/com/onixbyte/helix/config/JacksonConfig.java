package com.onixbyte.helix.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.onixbyte.helix.common.jackson.JacksonModules;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomiser() {
        return (builder) -> {
            builder.modules(JacksonModules.JAVA_TIME_MODULE);
            builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        };
    }
}
