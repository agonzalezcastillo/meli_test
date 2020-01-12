package com.meli.test.test.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.InvalidParameterException;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MatrixUtilsTest {
	
	@InjectMocks
	private MatrixUtils matrixUtils;
	
	private String[] dna;
	private String[] dna3;
	
	@Before
	public void setUp() throws Exception {
		
		dna = Arrays.array("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
		dna3 = Arrays.array("ASDQW","MNBJHG","POILKJ","GFDTRT","ÑLPQPS","CDFGHT");
		
	}
	
	@Test
	public void validateMatrixDimTestOK() {
		Boolean res = matrixUtils.validateMatrixDim(dna);
		assertEquals(true, res);
	}
	
	@Test(expected = InvalidParameterException.class)
	public void validateMatrixDimTestFail() {
		Boolean res = matrixUtils.validateMatrixDim(dna3);
		assertEquals(false, res);
	}

}
