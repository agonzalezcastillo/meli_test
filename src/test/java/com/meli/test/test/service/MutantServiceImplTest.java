package com.meli.test.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Vector;

import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.meli.test.test.repository.DnaRepository;
import com.meli.test.test.service.impl.MutantServiceImpl;
import com.meli.test.test.util.MatrixUtils;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceImplTest {
		
	@InjectMocks
	private MutantServiceImpl mutantService;
	
	@Mock
	private MatrixUtils matrixUtils;
	
	@Mock
	private DnaRepository dnaRepository; 
			
	private Boolean res;
	private String[] dna;
	private String[] dna2;
	private String[] dna3;
	private Integer dimension;
	Vector<Vector<String>> matrix;
	Vector<Vector<String>> matrix2;
	private Integer dnaSeqSize;
	private Integer dnaListSize;
	
	@Before
	public void setUp() throws Exception {
		
		res = false;
		dna = Arrays.array("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
		dna2 = Arrays.array("ASDQWE","MNBJHG","POILKJ","GFDTRT","ÑLPQPS","CDFGHT");
		dna3 = Arrays.array("ASDQW","MNBJHG","POILKJ","GFDTRT","ÑLPQPS","CDFGHT");
		dimension = dna.length;
		matrix = new Vector<Vector<String>>();
		matrix2 = new Vector<Vector<String>>();
		
		for(int i=0;i < dna.length;i++) {
			Vector<String> row = new Vector<String>();
			for(int j=0;j<dna.length;j++) {
				row.add(String.valueOf(dna[i].charAt(j)));
			}
			matrix.add(row);
		}
		
		
		for(int i=0;i < dna2.length;i++) {
			Vector<String> row2 = new Vector<String>();
			for(int j=0;j<dna2.length;j++) {
				row2.add(String.valueOf(dna2[i].charAt(j)));
			}
			matrix2.add(row2);
		}
		
		
	}
	
	@Test
	public void isMutantTest() {
		 when(matrixUtils.validateMatrixDim(any())).thenReturn(true);
		 when(dnaRepository.save(any())).thenReturn(true);
		 Boolean res =  mutantService.isMutant(dna);
		 verify(matrixUtils).validateMatrixDim(any());
		 assertEquals(true,  res);
		
	}
	
	@Test
	public void isMutantTestFail() {
		when(matrixUtils.validateMatrixDim(any())).thenReturn(true);
		when(dnaRepository.save(any())).thenReturn(true);
		 Boolean res =  mutantService.isMutant(dna2);
		 verify(matrixUtils).validateMatrixDim(any());
		 assertEquals(false,  res);
		
	}
	
	@Test
	public void validateMutantRowOK() {
		Boolean res = mutantService.validateMutantRow(matrix, dimension);
		assertEquals(true, res);
	}
	
	@Test
	public void validateMutantRowFail() {
		Boolean res = mutantService.validateMutantRow(matrix2, dimension);
		assertEquals(false, res);
	}
	
	@Test
	public void dnaLengthTest() {
		Integer res = dna[0].length();
		assertEquals(new Integer(6), res);
		
	}

}
