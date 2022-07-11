package com.brewery.wholesale.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brewery.wholesale.api.rest.response.ResponseHandler;
import com.brewery.wholesale.models.Wholesaler;
import com.brewery.wholesale.services.WholesalerService;
import com.brewery.wholesale.utils.Constants;

@RestController
@RequestMapping("/api/wholesalers")
public class WholesalerRestController {

	@Autowired
	private WholesalerService wholesalerService;

	@GetMapping("")
	public ResponseEntity<Object> getWholesalers() {
		List<Wholesaler> result = wholesalerService.findAll();

		if (result.isEmpty()) {
			return ResponseHandler.generateResponse(Constants.EMPTY_DATA, HttpStatus.NOT_FOUND, result);
		} else {
			return ResponseHandler.generateResponse(Constants.DATA_SUCCESSFULLY_RETREIVED, HttpStatus.OK, result);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getWholesalerById(@PathVariable Integer id) {
		Optional<Wholesaler> wholesaler = wholesalerService.findById(id);
		if (wholesaler.isPresent()) {
			return ResponseHandler.generateResponse(Constants.DATA_SUCCESSFULLY_RETREIVED, HttpStatus.OK, wholesaler);
		} else {
			return ResponseHandler.generateResponse(Constants.ERR_WHOLESALER_MUST_EXIST, HttpStatus.NOT_FOUND, null);
		}
	}

}