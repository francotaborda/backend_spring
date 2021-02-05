package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.sentry.Sentry;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//Sentry.capture("Application Started");
		SpringApplication.run(Application.class, args);
	}

}
