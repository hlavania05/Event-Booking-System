package com.eventbooking.event_booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            // Security scheme hata di
            .info(new Info()
                .title("Event Booking System")
                .version("1.0")
                .description("API documentation for Event Booking System"));
    }
}
