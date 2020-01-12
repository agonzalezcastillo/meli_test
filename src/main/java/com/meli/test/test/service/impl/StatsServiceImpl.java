package com.meli.test.test.service.impl;

import org.springframework.stereotype.Service;

import com.meli.test.test.model.StatsDTO;
import com.meli.test.test.repository.DnaRepository;
import com.meli.test.test.service.StatsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StatsServiceImpl implements StatsService {
	
	private DnaRepository dnaRepository;
	
	public StatsServiceImpl(DnaRepository dnaRepository) {
		this.dnaRepository = dnaRepository;
	}

	@Override
	public StatsDTO getStats() { 		
		
		StatsDTO stats = new StatsDTO();
		stats.setCountMutantDna(dnaRepository.countByisMutant(true));
		stats.setCounthumanDna(dnaRepository.countByisMutant(false));
		try {
			stats.setRatio(((double)stats.getCounthumanDna() / stats.getCountMutantDna()));
		}catch(ArithmeticException e) {
			log.error(e.getMessage());
		}
		return stats;
	}

}
