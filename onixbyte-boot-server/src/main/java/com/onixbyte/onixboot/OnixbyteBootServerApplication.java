package com.onixbyte.onixboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.onixbyte.onixboot.repository")
@EnableCaching
@SpringBootApplication
public class OnixbyteBootServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnixbyteBootServerApplication.class, args);
    }

}
