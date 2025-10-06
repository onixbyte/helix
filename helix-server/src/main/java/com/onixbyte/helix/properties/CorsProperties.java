package com.onixbyte.helix.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpMethod;

import java.time.Duration;

/**
 * Configuration properties class for Cross-Origin Resource Sharing (CORS) settings.
 * <p>
 * This class provides a type-safe way to bind CORS-related configuration properties from
 * application configuration files (such as {@code application.yml} or
 * {@code application.properties}) to a Java object. All properties are prefixed with
 * {@code app.cors} and are automatically mapped by Spring Boot's configuration property
 * binding mechanism.
 * <p>
 * <strong>CORS Overview:</strong> Cross-Origin Resource Sharing is a security feature implemented
 * by web browsers that restricts web pages from making requests to a different domain than the one
 * serving the web page. This class allows fine-grained control over CORS policies for the Helix
 * application's REST APIs.
 * <p>
 * <strong>Configuration Example:</strong>
 * <pre>{@code
 * app:
 *   cors:
 *     allowed-origins:
 *       - "https://example.com"
 *       - "https://app.example.com"
 *     allowed-methods:
 *       - GET
 *       - POST
 *       - PUT
 *       - DELETE
 *     allowed-headers:
 *       - "Content-Type"
 *       - "Authorization"
 *     allow-credentials: true
 *     max-age: PT1H
 * }</pre>
 * <p>
 * <strong>Security Considerations:</strong> When configuring CORS, be mindful of
 * security implications. Avoid using wildcard ({@code "*"}) for origins when
 * {@code allowCredentials} is set to {@code true}, as this can expose the application to
 * security vulnerabilities.
 * <p>
 * The class follows JavaBean conventions with getter and setter methods for each property,
 * making it compatible with Spring's property binding and validation frameworks.
 *
 * @param allowedHeaders      HTTP header names that are permitted in the actual request
 * @param allowedMethods      HTTP methods (e.g., GET, POST, PUT) that are permitted for cross-origin requests
 * @param allowedOrigins      origins (domains) that are allowed to make cross-origin requests. Use "*" to
 *                            allow all origins.
 * @param allowCredentials    indicating whether the client is allowed to send credentials (cookies,
 *                            HTTP authentication) with the cross-origin request
 * @param allowPrivateNetwork A flag indicating whether requests from a private network IP address
 *                            to a public network IP address are allowed
 * @param maxAge              maximum amount of time for which the results of a preflight request
 *                            can be cached by the client
 * @param exposedHeaders      HTTP header names that are allowed to be exposed to the client in a
 *                            cross-origin response
 * @author zihluwang
 * @see ConfigurationProperties
 * @see org.springframework.web.cors.CorsConfiguration
 * @see org.springframework.web.servlet.config.annotation.CorsRegistry
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.cors")
public record CorsProperties(
        @DefaultValue({"Content-Type", "Authorization"})
        String[] allowedHeaders,
        @DefaultValue({"get", "post", "put", "patch", "delete"})
        HttpMethod[] allowedMethods,
        String[] allowedOrigins,
        boolean allowCredentials,
        boolean allowPrivateNetwork,
        @DefaultValue("PT1H30M")
        Duration maxAge,
        @DefaultValue({"Content-Type", "Authorization"})
        String[] exposedHeaders
) {
}
