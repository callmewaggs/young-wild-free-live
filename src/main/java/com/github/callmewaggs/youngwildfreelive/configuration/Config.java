package com.github.callmewaggs.youngwildfreelive.configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
