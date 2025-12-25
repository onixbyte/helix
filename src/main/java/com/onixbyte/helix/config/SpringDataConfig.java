package com.onixbyte.helix.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.onixbyte.helix.repository"})
public class SpringDataConfig {
}
