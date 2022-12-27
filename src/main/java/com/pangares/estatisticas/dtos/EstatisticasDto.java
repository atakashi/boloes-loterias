package com.pangares.estatisticas.dtos;

import java.util.List;

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
public class EstatisticasDto {

	private List<NumeroFrequenciaDto> frequencias;
	
	private ParImparDto parImpar;
	
	private QuadranteDto quadrante;
	
}
