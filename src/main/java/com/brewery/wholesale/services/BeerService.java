package com.brewery.wholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brewery.wholesale.models.Beer;
import com.brewery.wholesale.repositories.BeerRepository;

@Service
public class BeerService {

	@Autowired
	private BeerRepository beerRepository;

	public List<Beer> findAllbyBrewery(Integer id) {
		return beerRepository.findAllByBreweryId(id);
	}

	public Optional<Beer> findById(Integer id) {
		return beerRepository.findById(id);
	}

	public Beer save(Beer beer) {
		return beerRepository.saveAndFlush(beer);
	}

	public void deleteById(int id) {
		beerRepository.deleteById(id);
	}

}