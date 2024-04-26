package com.example.citizen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CitizenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitizenApplication.class, args);
	}
}
