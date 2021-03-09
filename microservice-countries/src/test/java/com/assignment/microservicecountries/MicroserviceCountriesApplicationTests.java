package com.assignment.microservicecountries;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;



@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class MicroserviceCountriesApplicationTests {
	
	 @LocalServerPort
     public int port;

	 
	@Test
	@DisplayName("MicroserviceCountriesApplicationTests - Test Application context")	
	void contextLoads() {
	}
}
