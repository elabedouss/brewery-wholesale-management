package com.brewery.wholesale.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.brewery.wholesale.models.Brewery;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Integer>, JpaSpecificationExecutor<Brewery> {

}
