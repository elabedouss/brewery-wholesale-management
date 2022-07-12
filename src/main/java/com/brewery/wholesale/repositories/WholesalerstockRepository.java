package com.brewery.wholesale.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.brewery.wholesale.models.Wholesalerstock;
import com.brewery.wholesale.models.WholesalerstockId;

@Repository
public interface WholesalerstockRepository
		extends JpaRepository<Wholesalerstock, WholesalerstockId>, JpaSpecificationExecutor<Wholesalerstock> {

	Optional<Wholesalerstock> findById(WholesalerstockId id);
}
