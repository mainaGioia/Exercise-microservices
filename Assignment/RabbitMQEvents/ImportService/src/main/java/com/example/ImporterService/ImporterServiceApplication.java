package com.example.ImporterService;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ImporterServiceApplication {
	
	
	@Bean 
	public ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
	

	public static void main(String[] args) {
		SpringApplication.run(ImporterServiceApplication.class, args);
	}

}
