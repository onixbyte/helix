package com.onixbyte.helix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entrance.
 *
 * @author zihluwang
 * @see SpringBootApplication
 * @see SpringApplication
 * @since 1.0.0
 */
@SpringBootApplication
public class HelixApplication {

    /**
     * Main method that serves as the entry point for the Helix application.
     *
     * @param args command-line arguments passed to the application, which can be used to override
     *             default configuration properties or specify runtime options
     */
    public static void main(String[] args) {
        SpringApplication.run(HelixApplication.class, args);
    }

}
