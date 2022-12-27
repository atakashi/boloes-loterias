package com.pangares.estatisticas.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class NumeroFrequenciaDto {

	 private Integer numero;
	 
	 private Integer quantidadeOcorrencias;
	 
	 private QuadranteEnum quadrante;

}
