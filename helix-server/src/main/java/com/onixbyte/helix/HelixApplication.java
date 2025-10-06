package com.onixbyte.helix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main application class for the Helix server application.
 * <p>
 * This class serves as the entry point for the Helix Spring Boot application, providing the main
 * method that bootstraps the entire application context. The application is configured with several
 * key features and integrations:
 * <p>
 * <strong>MyBatis Integration:</strong> The {@link MapperScan} annotation automatically scans and
 * registers MyBatis mapper interfaces from the {@code com.onixbyte.helix.repository} package,
 * enabling seamless database operations through the MyBatis ORM framework.
 * <p>
 * <strong>Caching Support:</strong> The {@link EnableCaching} annotation activates Spring's caching
 * abstraction, allowing the application to leverage caching mechanisms for improved performance.
 * This enables the use of caching annotations such as {@code @Cacheable}, {@code @CacheEvict}, and
 * {@code @CachePut} throughout the application.
 * <p>
 * <strong>Auto-Configuration:</strong> The {@link SpringBootApplication} annotation combines
 * {@code @Configuration}, {@code @EnableAutoConfiguration}, and {@code @ComponentScan}, providing
 * comprehensive auto-configuration capabilities and component discovery for the entire application.
 * <p>
 * The Helix application is designed as a comprehensive business management system with role-based
 * access control (RBAC), user management, and organisational structure support. It provides RESTful
 * APIs for client applications and integrates with various external systems through configurable
 * identity providers.
 *
 * @author zihluwang
 * @see SpringBootApplication
 * @see MapperScan
 * @see EnableCaching
 * @see SpringApplication
 * @since 1.0.0
 */
@EnableCaching
@SpringBootApplication
public class HelixApplication {

    /**
     * Main method that serves as the entry point for the Helix application.
     * <p>
     * This method initialises and starts the Spring Boot application context, setting up all
     * configured beans, services, and web endpoints. The application will start an embedded web
     * server (typically Tomcat) and begin accepting HTTP requests on the configured port.
     * <p>
     * The method delegates to {@link SpringApplication#run(Class, String...)} which handles the
     * complete application bootstrap process, including:
     * <ul>
     * <li>Loading application properties and configuration</li>
     * <li>Initialising the Spring application context</li>
     * <li>Auto-configuring beans and components</li>
     * <li>Starting the embedded web server</li>
     * <li>Registering shutdown hooks for graceful termination</li>
     * </ul>
     *
     * @param args command-line arguments passed to the application, which can be used to override
     *             default configuration properties or specify runtime options
     */
    public static void main(String[] args) {
        SpringApplication.run(HelixApplication.class, args);
    }

}
