package com.assignment.microservicecountries.countries.details.exception;

public class CountriesServiceException extends RuntimeException {

	
	/**
	 * country not found exception
	 */
	private static final long serialVersionUID = 1L;

	public CountriesServiceException(String message) {
		super(message);

	}
}
