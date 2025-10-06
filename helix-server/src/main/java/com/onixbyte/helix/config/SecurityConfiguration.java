package com.onixbyte.helix.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.onixbyte.helix.properties.CorsProperties;
import com.onixbyte.helix.properties.TokenProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.stream.Stream;

/**
 * Configuration class for Spring Security components and policies.
 * <p>
 * This configuration class establishes comprehensive security settings for the Helix application,
 * including CORS policies, authentication mechanisms, password encoding, and JWT token handling.
 * It configures a stateless security architecture suitable for modern web applications and APIs.
 * <p>
 * Key security features configured:
 * <ul>
 *   <li>CORS (Cross-Origin Resource Sharing) configuration</li>
 *   <li>Stateless session management</li>
 *   <li>JWT-based authentication with HMAC256 algorithm</li>
 *   <li>BCrypt password encoding</li>
 *   <li>Method-level security annotations</li>
 *   <li>Custom authentication providers</li>
 * </ul>
 *
 * @author zihluwang
 * @see EnableWebSecurity
 * @see EnableMethodSecurity
 * @see TokenProperties
 * @see CorsProperties
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties({TokenProperties.class, CorsProperties.class})
public class SecurityConfiguration {

    /**
     * Creates a CORS configuration source based on application properties.
     * <p>
     * This method configures Cross-Origin Resource Sharing (CORS) policies using the
     * {@link CorsProperties} configuration. It sets up allowed origins, headers, methods,
     * credentials handling, and other CORS-related settings to enable secure cross-origin requests
     * from web browsers.
     * <p>
     * The configuration is applied globally to all endpoints (/**) within the application.
     *
     * @param properties the CORS configuration properties containing allowed origins, headers,
     *                   methods, etc
     * @return a configured {@link CorsConfigurationSource} for handling cross-origin requests
     * @see CorsProperties
     * @see CorsConfiguration
     * @see UrlBasedCorsConfigurationSource
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(
            CorsProperties properties
    ) {
        var corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(properties.getAllowCredentials());
        corsConfiguration.setAllowedOrigins(List.of(properties.getAllowedOrigins()));
        corsConfiguration.setAllowedHeaders(List.of(properties.getAllowedHeaders()));
        corsConfiguration.setAllowedMethods(Stream.of(properties.getAllowedMethods())
                .map(HttpMethod::name)
                .toList());
        corsConfiguration.setMaxAge(properties.getMaxAge());
        corsConfiguration.setAllowPrivateNetwork(properties.getAllowPrivateNetwork());
        corsConfiguration.setExposedHeaders(List.of(properties.getExposedHeaders()));

        var corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsConfigurationSource;
    }

    /**
     * Configures the Spring Security filter chain for HTTP requests.
     * <p>
     * This method establishes the core security policies for the application, including:
     * <ul>
     *   <li>CORS configuration integration</li>
     *   <li>CSRF protection disabled (suitable for stateless APIs)</li>
     *   <li>Stateless session management</li>
     *   <li>Request authorization rules with public and protected endpoints</li>
     * </ul>
     * <p>
     * The configuration permits access to error pages and authentication endpoints whilst requiring
     * authentication for all other requests. Logout endpoints require authentication to prevent
     * unauthorised session termination.
     *
     * @param httpSecurity            the HTTP security configuration builder
     * @param corsConfigurationSource the CORS configuration source for cross-origin requests
     * @return a configured {@link SecurityFilterChain} for processing HTTP requests
     * @throws Exception if any exception occurs during security filter chain construction
     * @see HttpSecurity
     * @see SecurityFilterChain
     * @see SessionCreationPolicy#STATELESS
     */
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            CorsConfigurationSource corsConfigurationSource
    ) throws Exception {
        return httpSecurity
                .cors((customiser) -> customiser
                        .configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement((customiser) -> customiser
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((customiser) -> customiser
                        .requestMatchers("/error", "/error/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/auth/logout").authenticated()
                        .anyRequest().authenticated())
                // .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Creates a password encoder for secure password hashing.
     * <p>
     * This method provides a {@link BCryptPasswordEncoder} instance that uses the BCrypt hashing
     * algorithm to securely encode passwords. BCrypt is a adaptive hash function designed for
     * password hashing that includes a salt to protect against rainbow table attacks and is
     * computationally expensive to resist brute-force attacks.
     * <p>
     * The encoder is used throughout the application for password verification during
     * authentication and for encoding new passwords during user registration.
     *
     * @return a {@link BCryptPasswordEncoder} instance for secure password operations
     * @see BCryptPasswordEncoder
     * @see PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Creates an authentication manager with custom authentication providers.
     * <p>
     * This method configures a {@link ProviderManager} that coordinates multiple authentication
     * providers to handle different authentication mechanisms within the application. The manager
     * attempts authentication using each configured provider until one succeeds or all fail.
     * <p>
     * Currently configured for extensibility to support various authentication providers such as
     * Microsoft Entra ID, local database authentication, or other identity providers as needed.
     *
     * @return a {@link ProviderManager} instance configured with authentication providers
     * @see ProviderManager
     * @see AuthenticationManager
     */
    // @Bean
    // public AuthenticationManager authenticationManager(
    // ) {
    //     return new ProviderManager(
    //     );
    // }

    /**
     * Creates the JWT signing algorithm using application token properties.
     * <p>
     * This method configures an HMAC256 algorithm instance using the secret key specified in
     * the {@link TokenProperties}. The algorithm is used for signing and verifying JWT tokens
     * throughout the application, ensuring token integrity and authenticity.
     * <p>
     * HMAC256 provides a good balance of security and performance for JWT token signing in
     * most applications.
     *
     * @param properties the token configuration properties containing the signing secret
     * @return a configured {@link Algorithm} instance for JWT token operations
     * @see Algorithm
     * @see TokenProperties
     */
    @Bean
    public Algorithm algorithm(TokenProperties properties) {
        return Algorithm.HMAC256(properties.getSecret());
    }
}
