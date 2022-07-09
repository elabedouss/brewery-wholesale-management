package com.brewery.wholesale.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.brewery.wholesale.models.Beer;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Integer>, JpaSpecificationExecutor<Beer> {

	List<Beer> findAllByBreweryId(Integer id);

}
