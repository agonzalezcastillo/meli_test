package com.meli.test.test.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Component
@Getter
@Setter
public class StatsDTO implements Serializable {

	private Long countMutantDna;
	private Long counthumanDna;
	private Double ratio;
}
