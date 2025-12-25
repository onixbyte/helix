package com.onixbyte.helix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Application entrance.
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
     *
     * @param args command-line arguments passed to the application, which can be used to override
     *             default configuration properties or specify runtime options
     */
    public static void main(String[] args) {
        SpringApplication.run(HelixApplication.class, args);
    }

}
