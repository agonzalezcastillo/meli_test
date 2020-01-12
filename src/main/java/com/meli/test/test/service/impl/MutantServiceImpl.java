package com.meli.test.test.service.impl;

import java.util.Vector;

import org.springframework.stereotype.Service;

import com.meli.test.test.model.DnaDTO;
import com.meli.test.test.repository.DnaRepository;
import com.meli.test.test.service.MutantService;
import com.meli.test.test.util.MatrixUtils;
import com.mongodb.DuplicateKeyException;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class MutantServiceImpl implements MutantService {
	
	private MatrixUtils matrixUtils;
	
	private DnaRepository dnaRepository;
	
	public MutantServiceImpl(MatrixUtils matrixUtils, DnaRepository dnaRepository) {
		this.matrixUtils = matrixUtils;
		this.dnaRepository = dnaRepository;
	}
	
	Integer mutant = 0;
	private Integer aCount = 0;
	private Integer cCount = 0;
	private Integer gCount = 0;
	private Integer tCount = 0;
			
	@Override
	public Boolean isMutant(String[] dna) {
				
		Integer dnaSeqSize = dna[0].length();
		Integer dnaListSize = dna.length;
		
		if(matrixUtils.validateMatrixDim(dna)) {
		
		Vector<Vector<String>> matrix = new Vector<Vector<String>>(); 
		for(int i=0;i < dnaListSize;i++) {
			Vector<String> row = new Vector<String>();
			for(int j=0;j<dnaSeqSize;j++) {
				row.add(String.valueOf(dna[i].charAt(j)));
			}
			matrix.add(row);
		}
		if(this.validateMutantDiagonal(matrix,dnaListSize) ||
				this.validateMutantColumn(matrix, dnaListSize) ||
				this.validateMutantRow(matrix, dnaListSize)) {
			
			DnaDTO dnadto = new DnaDTO();			
			dnadto.setDnaStr(String.join("", dna));
			dnadto.setIsMutant(true);
			try {
				dnaRepository.save(dnadto);
			}catch(Exception e) {				
				log.info("DNA String already stored");				
			}
			return true;
		}else {
			DnaDTO dnadto = new DnaDTO();			
			dnadto.setDnaStr(String.join("", dna));
			dnadto.setIsMutant(false);
			try {
				dnaRepository.save(dnadto);
			}catch(Exception e) {				
				log.info("DNA String already stored");				
			}
			return false;
		}			
		}
		
		return false;
	}
	
	public Boolean validateMutantRow(Vector<Vector<String>> matrix, Integer dimension) {
		this.clearVars();
		Integer aux = 0;		
		String currentStr = "";
		String prevStr = "";
		for(int j=0;j<dimension;j++) {
			 aux = 1;
			 currentStr = "";
			 prevStr = "";
			for(int i=0;i<dimension;i++) {				
				if(i>0) {
					currentStr = matrix.get(j).get(i);
					prevStr = matrix.get(j).get(i-1);
					if(prevStr.equalsIgnoreCase(currentStr))
						chainCount(prevStr);					
				}						
		}
			if(aCount >= 4 || tCount >= 4 || cCount >= 4 || gCount >= 4)
				return true;
		}
		return false;			
	}
	
	public Boolean validateMutantColumn(Vector<Vector<String>> matrix, Integer dimension) {
		this.clearVars();
		Integer aux = 0;		
		String currentStr = "";
		String prevStr = "";
		for(int j=0;j<dimension;j++) {
			 aux = 1;
			 currentStr = "";
			 prevStr = "";
			for(int i=0;i<dimension;i++) {				
				if(i>0) {
					currentStr = matrix.get(i).get(j);
					prevStr = matrix.get(i-1).get(j);
					if(prevStr.equalsIgnoreCase(currentStr))
						chainCount(prevStr);					
				}						
		}
			if(aCount >= 4 || tCount >= 4 || cCount >= 4 || gCount >= 4)
				return true;
		}
		return false;
	}
	
	public Boolean validateMutantDiagonal(Vector<Vector<String>> matrix, Integer dimension) {
		this.clearVars();
		String currentStr = "";
		String prevStr = "";
	for(int i=0;i<dimension;i++) {		
		if(i>0) {
			currentStr = matrix.get(i).get(i);
			prevStr = matrix.get(i-1).get(i-1);
			if(prevStr.equalsIgnoreCase(currentStr)) {
				chainCount(prevStr);
			}
								
		}						
	}
			return(mutantChain(aCount,tCount,cCount,gCount));			
	}
	
	private Boolean mutantChain(Integer a,Integer t,Integer c,Integer g) {
		if(a >= 4)
			return true;
		else if(t >= 4)
			return true;
		else if(c >= 4)
			return true;
		else if(g >= 4)
			return true;
		else
			return false;
	}
	
	private void chainCount(String str) {
		switch(str){
		case "A":
			aCount++;
			break;
		case "T":
			tCount++;
			break;
		case "C":
			cCount++;
			break;
		case "G":
			gCount++;
			break;
		}	
	}
	
	private void clearVars() {
		aCount = 1;
		cCount = 1;
		gCount = 1;
		tCount = 1;
	}
	
}
