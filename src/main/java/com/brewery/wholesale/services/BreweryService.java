package com.brewery.wholesale.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brewery.wholesale.repositories.BreweryRepository;

@Service
public class BreweryService {

	@Autowired
	private BreweryRepository breweryRepository;

	public Boolean exist(int id) {
		return breweryRepository.existsById(id);
	}

}