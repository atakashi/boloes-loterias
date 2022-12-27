package com.pangares.estatisticas.dtos;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum QuadranteEnum {

	QUADRANTE_1(1, new Integer[] {1, 2, 3, 4, 5, 11, 12, 13, 14, 15, 21, 22, 23, 24, 25}),
	QUADRANTE_2(2, new Integer[] {6, 7, 8, 9, 10, 16, 17, 18, 19, 20, 26, 27, 28, 29, 30}),
	QUADRANTE_3(3, new Integer[] {31, 32, 33, 34, 35, 41, 42, 43, 44, 45, 51, 52, 53, 54, 55}),
	QUADRANTE_4(4, new Integer[] {36, 37, 38, 39, 40, 46, 47, 48, 49, 50, 56, 57, 58, 59, 60});
	
	@SuppressWarnings("unused")
	private int quadrante;
	private Set<Integer> numeros;
	
	private QuadranteEnum(int quadrante, Integer[] numeros) {
		this.quadrante = quadrante;
		this.numeros = new HashSet<Integer>(Arrays.<Integer>asList(numeros));
	}
	
	public static QuadranteEnum qualQuadrante(Integer numero) {
		QuadranteEnum quadrante = null;
		
		if (QUADRANTE_1.numeros.contains(numero)) {
			quadrante = QUADRANTE_1;
		}
		if (QUADRANTE_2.numeros.contains(numero)) {
			quadrante = QUADRANTE_2;
		}
		if (QUADRANTE_3.numeros.contains(numero)) {
			quadrante = QUADRANTE_3;
		}
		if (QUADRANTE_4.numeros.contains(numero)) {
			quadrante = QUADRANTE_4;
		}
		
		return quadrante;
	}
	
}
