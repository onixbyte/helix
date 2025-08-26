package com.onixbyte.helix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Web flux configuration.
 *
 * @author zihluwang
 */
@Configuration
public class WebFluxConfiguration {

    /**
     * A web client, for sending requests to other hosts.
     *
     * @return a commonly used web client
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .build();
    }
}
