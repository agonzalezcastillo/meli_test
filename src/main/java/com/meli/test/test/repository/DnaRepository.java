package com.meli.test.test.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.meli.test.test.model.DnaDTO;

@Repository
public interface DnaRepository extends MongoRepository<DnaDTO, Long> {

	Long countByisMutant(Boolean isMutant);
	
}
