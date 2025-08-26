package com.onixbyte.helix.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.onixbyte.helix.properties.CorsProperties;
import com.onixbyte.helix.properties.TokenProperties;
import com.onixbyte.helix.security.providers.MsalAuthenticationProvider;
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
 * Spring security configuration.
 *
 * @author zihluwang
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableConfigurationProperties({TokenProperties.class, CorsProperties.class})
public class SecurityConfiguration {

    /**
     * CORS configuration source.
     *
     * @param properties CORS configuration properties
     * @return configured CORS configuration source
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
     * Spring security filter chain.
     *
     * @param httpSecurity            HTTP security context
     * @param corsConfigurationSource cross-origin configuration
     * @return built security filter chain
     * @throws Exception if any exception occurred while building HTTP security filter chain
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
     * Register a password encoder that helps encode plain text password in Spring context.
     *
     * @return a {@link BCryptPasswordEncoder} instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Build an authentication manager with custom authentication providers.
     *
     * @param msalAuthenticationProvider authentication provider of Microsoft Entra ID
     * @return an {@link ProviderManager} instance with given authentication providers
     */
    @Bean
    public AuthenticationManager authenticationManager(
            MsalAuthenticationProvider msalAuthenticationProvider
    ) {
        return new ProviderManager(
                msalAuthenticationProvider
        );
    }

    /**
     * The algorithm to sign tokens.
     *
     * @param properties token properties
     * @return built algorithm
     */
    @Bean
    public Algorithm algorithm(TokenProperties properties) {
        return Algorithm.HMAC256(properties.getSecret());
    }
}
