package com.assignment.microservicecountries.countries.details.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.assignment.microservicecountries.countries.details.exception.CountryNotFoundException;

@WebMvcTest(CountryDetailsController.class)
public class CountryDetailsControllerIntegrationTest {
	
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 private final String JSON_RESPONSE = "{\"name\":\"Finland\",\"country_code\":\"FI\",\"capital\":\"Helsinki\",\"population\":5491817,\"flag_file_url\":\"https://restcountries.eu/data/fin.svg\"}";

	 @Test
		public void getCountriesEndPointTest() throws Exception {
			this.mockMvc.perform(get("/countries/"))
					.andExpect(status().isOk());
		}

		@Test
		public void getCountryEndPointTest() throws Exception {
			this.mockMvc.perform(get("/countries/{name}","Finland"))
					.andExpect(status().isOk())
					.andExpect(result -> assertEquals(JSON_RESPONSE, result.getResponse().getContentAsString()));
			;
		}

		@Test	
		public void getInvalidCountryEndPointTest() throws Exception {
			this.mockMvc.perform(get("/countries/{name}", "Ind12"))
					.andExpect(result -> assertTrue(result.getResolvedException() instanceof CountryNotFoundException))
					.andExpect(result -> assertEquals("country not found for the requested country", result.getResolvedException().getMessage()));
		}

//		@Ignore
//		@Test
//		//Test needed to be done while being disconnected from the internet
//		public void getServerNotConnectedException() throws Exception {
//			this.mockMvc.perform(get("/countries/{name}", "India"))
//					.andExpect(result -> assertTrue(result.getResolvedException() instanceof CountriesServiceException))
//					.andExpect(result -> assertEquals("Error fetching data from external countries service", result.getResolvedException().getMessage()));
//		}

}
