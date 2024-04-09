package com.example.medicalservice.configuration.feign;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
public class AuthClientConfig {
    @Bean
    public RequestInterceptor noneRequestInterceptor() {
        return template -> log.info("Requesting for new jwt token");
    }
}
