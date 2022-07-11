package com.brewery.wholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brewery.wholesale.models.Brewery;
import com.brewery.wholesale.repositories.BreweryRepository;

@Service
public class BreweryService {

	@Autowired
	private BreweryRepository breweryRepository;

	public List<Brewery> findAll() {
		return breweryRepository.findAll();
	}

	public Optional<Brewery> findById(Integer id) {
		return breweryRepository.findById(id);
	}

}