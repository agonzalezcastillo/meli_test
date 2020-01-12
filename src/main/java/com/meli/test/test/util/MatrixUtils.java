package com.meli.test.test.util;

import java.security.InvalidParameterException;

import org.springframework.stereotype.Component;

@Component
public class MatrixUtils {
	
	public Boolean validateMatrixDim(String[] dna) {
		if(dna[0].length() != dna.length)
			throw new InvalidParameterException("DNA table size is not NxN");
		else
			return true;
	}

}
