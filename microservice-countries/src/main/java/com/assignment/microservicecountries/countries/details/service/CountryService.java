package com.assignment.microservicecountries.countries.details.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.microservicecountries.countries.details.domain.Countries;
import com.assignment.microservicecountries.countries.details.domain.Country;
import com.assignment.microservicecountries.countries.details.domain.CountryDetail;
@Service
public class CountryService {

	public Country populateCountry(LinkedHashMap<String, String> countriesResponseMap) {

		return new Country(countriesResponseMap.get("name"), countriesResponseMap.get("alpha2Code"));
	}

	public CountryDetail populateCountryDetail(LinkedHashMap<String, Object> countriesResponseMap) {

		CountryDetail countryDetail = new CountryDetail((String) countriesResponseMap.get("name"),
				(String) countriesResponseMap.get("alpha2Code"));

		countryDetail.setCapital((String) countriesResponseMap.get("capital"));
		countryDetail.setPopulation((Integer) countriesResponseMap.get("population"));
		countryDetail.setFlagFileUrl((String) countriesResponseMap.get("flag"));

		return countryDetail;
	}

	public Countries populateCountries(List<Country> listofcountries) {
		Countries countries = new Countries();
		countries.setCountries(listofcountries);
		return countries;
	}

}
