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
public class QuadranteDto {

	private FrequenciaDto quadrante1;
	private FrequenciaDto quadrante2;
	private FrequenciaDto quadrante3;
	private FrequenciaDto quadrante4;
}
