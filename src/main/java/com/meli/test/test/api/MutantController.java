package com.meli.test.test.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meli.test.test.model.DnaDTO;
import com.meli.test.test.model.StatsDTO;
import com.meli.test.test.service.MutantService;
import com.meli.test.test.service.StatsService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MutantController {
	
	private MutantService mutantService;
	
	private StatsService statsService; 
	

	@RequestMapping(value = "/mutant" , consumes= {"application/json"}, 
			produces = {"application/json"}, 
			method = RequestMethod.POST)
	public ResponseEntity<Boolean> mutant(@Valid 
			@RequestBody DnaDTO dnaDto) {
		
		Boolean res = mutantService.isMutant(dnaDto.getDna());				
				
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stats" ,
			produces = {"application/json"}, 
			method = RequestMethod.GET)
	public ResponseEntity<StatsDTO> mutantStats() {
		
		StatsDTO res = statsService.getStats();				
				
		return new ResponseEntity<>(res, HttpStatus.OK);
	}	
	
}