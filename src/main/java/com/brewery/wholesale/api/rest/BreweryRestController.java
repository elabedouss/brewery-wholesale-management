package com.brewery.wholesale.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brewery.wholesale.api.rest.response.ResponseHandler;
import com.brewery.wholesale.models.Brewery;
import com.brewery.wholesale.services.BreweryService;
import com.brewery.wholesale.utils.Constants;

@RestController
@RequestMapping("/api/breweries")
public class BreweryRestController {

	@Autowired
	private BreweryService breweryService;

	@GetMapping("")
	public ResponseEntity<Object> getBreweries() {
		List<Brewery> result = breweryService.findAll();
		if (result.isEmpty()) {
			return ResponseHandler.generateResponse(Constants.EMPTY_DATA, HttpStatus.NOT_FOUND, result);
		} else {
			return ResponseHandler.generateResponse(Constants.DATA_SUCCESSFULLY_RETREIVED, HttpStatus.OK, result);
		}
	}

}