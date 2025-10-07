package com.onixbyte.helix.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.onixbyte.helix.repository"})
public class MyBatisConfig {
}
