package com.assignment.microservicecountries.countries.details.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.assignment.microservicecountries.countries.details.domain.Countries;
import com.assignment.microservicecountries.countries.details.domain.Country;
import com.assignment.microservicecountries.countries.details.domain.CountryDetail;
import com.assignment.microservicecountries.countries.details.exception.CountriesServiceException;
import com.assignment.microservicecountries.countries.details.exception.CountryNotFoundException;
import com.assignment.microservicecountries.countries.details.service.CountryService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CountryDetailsController {

	@Autowired
	private Environment environment;
	private RestTemplate restTemplate = null;

	private CountryService countryService = null;

	private static final Logger logger = LoggerFactory.getLogger(CountryDetailsController.class);

	public CountryDetailsController(RestTemplate restTemplate, CountryService countryService) {
		this.restTemplate = restTemplate;
		this.countryService = countryService;
	}

	@GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Countries> getAllCountriesDetails() {

		ArrayList<LinkedHashMap<String, String>> countriesResponseList;

		String servicename = environment.getProperty("country.details.all.service");
		try {
			countriesResponseList = restTemplate.getForObject(servicename, ArrayList.class);

			if (countriesResponseList != null) {
				List<Country> countryList = countriesResponseList.stream()
						.map(linkedHashMap -> countryService.populateCountry(linkedHashMap))
						.collect(Collectors.toList());
				Countries countries = countryService.populateCountries(countryList);
				logger.debug("Country Details API Response  getCountriesDetails:: {}", countries);
				return new ResponseEntity<>(countries, HttpStatus.OK);
			} else
				throw new CountryNotFoundException("country not found for the requested");

		} catch (HttpClientErrorException e) {
			logger.error(e.getMessage());
			throw new CountriesServiceException("Error fetching data from external countries service");
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new CountriesServiceException("Error fetching data from external countries service");
		}

	}

	@GetMapping(value = "/countries/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CountryDetail> getCountryDetailsbyCountryName(@PathVariable(required = false) String name) {
		
		logger.info("get country details for country", name);

		String servicename = environment.getProperty("country.details.by.countryname");
		String filter = environment.getProperty("country.details.by.countryname.filter");

		ArrayList<LinkedHashMap<String, Object>> countriesResponseDataList;
		CountryDetail countryDetail;
		try {
			countriesResponseDataList = restTemplate.getForObject(servicename + name + filter, ArrayList.class);
			if (countriesResponseDataList != null) {
				Optional<CountryDetail> countryDetailsearch = countriesResponseDataList.stream().findFirst()
						.map(linkedHashMap -> countryService.populateCountryDetail(linkedHashMap));
					if (countryDetailsearch.isPresent()) {
						countryDetail = countryDetailsearch.get();
						logger.debug("Country Details API Response  getCountryDetailsbyCountryName:: {}", countryDetail);
						return new ResponseEntity<>(countryDetail, HttpStatus.OK);
					
				} else
					throw new CountryNotFoundException("country not found for the requested country" );
			} else {
				throw new CountryNotFoundException("country not found for the requested country" );
            }
		} catch (HttpClientErrorException e) {
			logger.error(e.getMessage());
			throw new CountryNotFoundException("country not found for the requested country" );
		}catch (Exception e) {
            logger.error(e.getMessage());
            throw  new CountriesServiceException("Error fetching data from external countries service" );
        }
	}
}
