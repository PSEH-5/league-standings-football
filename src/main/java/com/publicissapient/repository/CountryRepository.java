package com.publicissapient.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.publicissapient.config.DataConfiguration;
import com.publicissapient.model.Country;
import com.publicissapient.service.exceptions.FootBallException;

@Component
public class CountryRepository {

	@Autowired
    DataConfiguration config;

	public ResponseEntity<Country[]>  getCountryId(String countryName) throws FootBallException {

		RestTemplate resttemplate = new RestTemplate();
		ResponseEntity<Country[]> responsecountryList = resttemplate.getForEntity(config.getGetCountriesUrl(),
				Country[].class);
	return responsecountryList;

	}
}
