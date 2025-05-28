package com.eventbooking.event_booking;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventBookingApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
                              .ignoreIfMissing()  
                              .load();
		// Manually set environment variables from .env file
        System.setProperty("spring.application.name", dotenv.get("SPRING_APPLICATION_NAME"));
        System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DB_USERNAME"));
        System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));

        System.setProperty("spring.jpa.hibernate.ddl-auto", dotenv.get("HIBERNATE_DDL_AUTO"));
        System.setProperty("spring.jpa.properties.hibernate.dialect", dotenv.get("HIBERNATE_DIALECT"));
        System.setProperty("spring.jpa.show-sql", dotenv.get("SHOW_SQL"));

        System.setProperty("jwt.secret", dotenv.get("JWT_SECRET"));
        System.setProperty("jwt.expiration", dotenv.get("JWT_EXPIRATION"));
		SpringApplication.run(EventBookingApplication.class, args);
	}

}
