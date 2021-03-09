package com.assignment.microservicecountries.countries.details.exception;

public class CountryNotFoundException extends RuntimeException {

	/**
	 * country not found exception
	 */
	private static final long serialVersionUID = 1L;

	public CountryNotFoundException(String message) {
		super(message);

	}
}
