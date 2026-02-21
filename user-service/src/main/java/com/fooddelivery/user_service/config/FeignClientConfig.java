package com.fooddelivery.user_service.config;

import feign.RequestInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    private final HttpServletRequest request;

    public FeignClientConfig(HttpServletRequest request) {
        this.request = request;
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            // Get Authorization header from incoming request
            String authHeader = request.getHeader("Authorization");

            // Only add Authorization if it's present
            if (authHeader != null && !authHeader.isBlank()) {
                template.header("Authorization", authHeader);
            }
        };
    }
}