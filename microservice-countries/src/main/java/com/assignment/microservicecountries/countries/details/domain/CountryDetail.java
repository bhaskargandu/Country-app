package com.assignment.microservicecountries.countries.details.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"name","countryCode","capital", "population", "flagFileUrl" })
public class CountryDetail extends Country {

	public CountryDetail(String name, String countryCode) {
        super(name, countryCode);
    }
	private String capital;
    private Integer population;

    @JsonProperty("flag_file_url")
    private String flagFileUrl;
}
