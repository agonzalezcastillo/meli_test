package com.meli.test.test.model;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Component
@Getter
@Setter
@Document(collection = "dnas")
public class DnaDTO implements Serializable {		
	
	
	@Indexed(unique=true)
	private String dnaStr;
	
	@Transient
	private String[] dna;	
	
	private Boolean isMutant;

	
}
