package com.onixbyte.helix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.onixbyte.helix.repository")
@EnableCaching
@SpringBootApplication
public class HelixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelixApplication.class, args);
    }

}
