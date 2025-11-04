package com.onixbyte.helix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Main application class for the Helix server application.
 *
 * @author zihluwang
 * @see SpringBootApplication
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
