package com.brewery.wholesale.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.brewery.wholesale.models.WholesalerStock;

@Repository
public interface WholesalerStockRepository
		extends JpaRepository<WholesalerStock, Integer>, JpaSpecificationExecutor<WholesalerStock> {

}
