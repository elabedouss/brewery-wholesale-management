package com.brewery.wholesale.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brewery.wholesale.models.Wholesaler;
import com.brewery.wholesale.repositories.WholesalerRepository;

@Service
public class WholesalerService {

	@Autowired
	private WholesalerRepository wholesalerRepository;

	public List<Wholesaler> findAll() {
		return wholesalerRepository.findAll();
	}

	public Optional<Wholesaler> findById(Integer id) {
		return wholesalerRepository.findById(id);
	}
}