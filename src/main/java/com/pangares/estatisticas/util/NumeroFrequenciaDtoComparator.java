package com.pangares.estatisticas.util;

import java.util.Comparator;

import com.pangares.estatisticas.dtos.NumeroFrequenciaDto;

public class NumeroFrequenciaDtoComparator implements Comparator<NumeroFrequenciaDto> {

	private boolean ascendente = true;
	
	private NumeroFrequenciaDtoComparator(boolean isAscendente) {
		this.ascendente = isAscendente;
	}
	
	public static NumeroFrequenciaDtoComparator build() {
		return new NumeroFrequenciaDtoComparator(true);
	}
	
	public static NumeroFrequenciaDtoComparator build(boolean isAscendente) {
		return new NumeroFrequenciaDtoComparator(isAscendente);
	}
	
	@Override
	public int compare(NumeroFrequenciaDto o1, NumeroFrequenciaDto o2) {
		int comparator = 0;
		if (o1.getQuantidadeOcorrencias() > o2.getQuantidadeOcorrencias()) {
			comparator = 1;
		} else if (o1.getQuantidadeOcorrencias() < o2.getQuantidadeOcorrencias()) {
			comparator =  -1;
		}

		if (o1.getQuantidadeOcorrencias() == o2.getQuantidadeOcorrencias()) {
			if (o1.getNumero() > o2.getNumero()) {
				comparator =  1;
			} else if (o1.getNumero() < o2.getNumero()) {
				comparator =  -1;
			}
		}

		return comparator * this.orderBy();
	}
	
	private int orderBy() {
		return this.ascendente ? 1 : (-1);
	}
}
