package com.onixbyte.helix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration class for Spring WebFlux reactive web components.
 * <p>
 * This configuration class provides beans for reactive web programming components within the Helix
 * application. It configures WebClient instances and other reactive web-related components that
 * enable non-blocking, asynchronous HTTP communication with external services.
 * <p>
 * The configuration supports:
 * <ul>
 *   <li>Reactive HTTP client configuration</li>
 *   <li>Non-blocking I/O operations</li>
 *   <li>Asynchronous request/response handling</li>
 * </ul>
 *
 * @author zihluwang
 * @since 1.0.0
 * @see WebClient
 * @see Configuration
 */
@Configuration
public class WebFluxConfig {

    /**
     * Creates a reactive WebClient for HTTP communication with external services.
     * <p>
     * This method configures a {@link WebClient} instance that provides a modern, reactive approach
     * to HTTP client operations. The WebClient supports non-blocking I/O and is built on top of
     * Reactor Netty, making it suitable for high-performance, scalable applications.
     * <p>
     * The client is configured with default settings and can be used throughout the application for
     * making HTTP requests to external APIs, microservices, or other web resources in a reactive,
     * non-blocking manner.
     * <p>
     * Key features:
     * <ul>
     *   <li>Non-blocking I/O operations</li>
     *   <li>Reactive streams support</li>
     *   <li>Built-in support for JSON serialisation/deserialisation</li>
     *   <li>Configurable timeouts and retry mechanisms</li>
     * </ul>
     *
     * @return a configured {@link WebClient} instance for reactive HTTP operations
     * @see WebClient
     * @see WebClient.Builder
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .build();
    }
}
