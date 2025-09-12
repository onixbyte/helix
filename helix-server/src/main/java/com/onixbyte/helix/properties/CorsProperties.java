package com.onixbyte.helix.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
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
 * @author zihluwang
 * @see ConfigurationProperties
 * @see org.springframework.web.cors.CorsConfiguration
 * @see org.springframework.web.servlet.config.annotation.CorsRegistry
 * @since 1.0.0
 */
@ConfigurationProperties(prefix = "app.cors")
public class CorsProperties {

    /**
     * The array of HTTP header names that are permitted in the actual request.
     */
    private String[] allowedHeaders;

    /**
     * The array of HTTP methods (e.g., GET, POST, PUT) that are permitted for cross-origin requests.
     */
    private HttpMethod[] allowedMethods;

    /**
     * The array of origins (domains) that are allowed to make cross-origin requests. Use "*" to
     * allow all origins.
     */
    private String[] allowedOrigins;

    /**
     * A flag indicating whether the client is allowed to send credentials (cookies,
     * HTTP authentication) with the cross-origin request.
     */
    private Boolean allowCredentials;

    /**
     * A flag indicating whether requests from a private network IP address to a public network IP
     * address are allowed.
     */
    private Boolean allowPrivateNetwork;

    /**
     * The maximum amount of time (in seconds) for which the results of a preflight request can be
     * cached by the client.
     */
    private Duration maxAge;

    /**
     * The array of HTTP header names that are allowed to be exposed to the client in a
     * cross-origin response.
     */
    private String[] exposedHeaders;

    /**
     * Constructs a new {@code CorsProperties} instance. This default constructor is typically used
     * by the Spring framework to instantiate the properties bean.
     */
    public CorsProperties() {
        // Default constructor
    }

    /**
     * Retrieves the array of allowed HTTP header names for CORS requests.
     *
     * @return an array of strings representing allowed header names
     */
    public String[] getAllowedHeaders() {
        return allowedHeaders;
    }

    /**
     * Sets the array of allowed HTTP header names for CORS requests.
     *
     * @param allowedHeaders an array of strings representing allowed header names
     */
    public void setAllowedHeaders(String[] allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    /**
     * Retrieves the array of allowed HTTP methods for CORS requests.
     *
     * @return an array of {@code HttpMethod} representing allowed methods
     */
    public HttpMethod[] getAllowedMethods() {
        return allowedMethods;
    }

    /**
     * Sets the array of allowed HTTP methods for CORS requests.
     *
     * @param allowedMethods an array of {@code HttpMethod} representing allowed methods
     */
    public void setAllowedMethods(HttpMethod[] allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    /**
     * Retrieves the array of allowed origins for CORS requests.
     *
     * @return an array of strings representing allowed origin URLs
     */
    public String[] getAllowedOrigins() {
        return allowedOrigins;
    }

    /**
     * Sets the array of allowed origins for CORS requests.
     *
     * @param allowedOrigins an array of strings representing allowed origin URLs
     */
    public void setAllowedOrigins(String[] allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    /**
     * Checks if credentials are allowed for CORS requests.
     *
     * @return {@code true} if credentials are allowed, {@code false} otherwise
     */
    public Boolean getAllowCredentials() {
        return allowCredentials;
    }

    /**
     * Sets whether credentials are allowed for CORS requests.
     *
     * @param allowCredentials {@code true} to allow credentials, {@code false} otherwise
     */
    public void setAllowCredentials(Boolean allowCredentials) {
        this.allowCredentials = allowCredentials;
    }

    /**
     * Checks if private network access is allowed for CORS requests.
     *
     * @return {@code true} if private network access is allowed, {@code false} otherwise
     */
    public Boolean getAllowPrivateNetwork() {
        return allowPrivateNetwork;
    }

    /**
     * Sets whether private network access is allowed for CORS requests.
     *
     * @param allowPrivateNetwork {@code true} to allow private network access, {@code false} otherwise
     */
    public void setAllowPrivateNetwork(Boolean allowPrivateNetwork) {
        this.allowPrivateNetwork = allowPrivateNetwork;
    }

    /**
     * Retrieves the maximum age for CORS preflight request caching.
     *
     * @return a {@code Duration} representing the maximum age
     */
    public Duration getMaxAge() {
        return maxAge;
    }

    /**
     * Sets the maximum age for CORS preflight request caching.
     *
     * @param maxAge a {@code Duration} representing the maximum age
     */
    public void setMaxAge(Duration maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * Retrieves the array of HTTP header names that can be exposed to the client.
     *
     * @return an array of strings representing exposed header names
     */
    public String[] getExposedHeaders() {
        return exposedHeaders;
    }

    /**
     * Sets the array of HTTP header names that can be exposed to the client.
     *
     * @param exposedHeaders an array of strings representing exposed header names
     */
    public void setExposedHeaders(String[] exposedHeaders) {
        this.exposedHeaders = exposedHeaders;
    }
}
