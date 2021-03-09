package com.assignment.microservicecountries.countries.details.controller;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CountryDetailsResponse implements Serializable {

	
	 String name;
	 String alpha2Code;
	 String capital;
	 Integer population;
	 String flag;

}