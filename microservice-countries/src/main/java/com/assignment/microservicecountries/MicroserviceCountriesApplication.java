package com.assignment.microservicecountries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan("com.*") 
public class MicroserviceCountriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCountriesApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		
		
		return new RestTemplate();
	}
	
	
}
