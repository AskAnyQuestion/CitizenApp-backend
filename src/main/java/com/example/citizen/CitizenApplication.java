package com.example.citizen;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CitizenApplication {
    @Bean
	FirebaseMessaging firebaseMessaging() throws Exception
	{
		GoogleCredentials googleCredentials = GoogleCredentials.
				fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
		FirebaseOptions firebaseOptions = FirebaseOptions.builder()
				.setCredentials(googleCredentials).build();
		FirebaseApp firebaseApp = FirebaseApp.initializeApp(firebaseOptions, "citizenApp");
		return FirebaseMessaging.getInstance(firebaseApp);
	}

	public static void main(String[] args) {
		SpringApplication.run(CitizenApplication.class, args);
	}
}
