package com.brewery.wholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brewery.wholesale.models.Wholesalerstock;
import com.brewery.wholesale.models.WholesalerstockId;
import com.brewery.wholesale.repositories.WholesalerstockRepository;

@Service
public class WholesalerStockService {

	@Autowired
	private WholesalerstockRepository wholesalerstockRepository;

	public List<Wholesalerstock> findAll() {
		return wholesalerstockRepository.findAll();
	}

	public Optional<Wholesalerstock> findById(WholesalerstockId id) {
		return wholesalerstockRepository.findById(id);
	}

	public Wholesalerstock save(Wholesalerstock wholesalerStock) {
		return wholesalerstockRepository.saveAndFlush(wholesalerStock);
	}
}