package com.example.realtimestreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RealtimeStreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealtimeStreamingApplication.class, args);
	}

	// jenkins 실험123
}
