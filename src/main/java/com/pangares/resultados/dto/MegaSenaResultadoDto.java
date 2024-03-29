package com.pangares.resultados.dto;

import static com.pangares.resultados.configuration.ResultadoConstants.BRAZILIAN_DATE_FORMAT;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MegaSenaResultadoDto {
	
	@JsonProperty("numero")
	private Long numeroConcurso;
	
	@JsonProperty("dataApuracao")
	@JsonFormat(shape = Shape.STRING, pattern = BRAZILIAN_DATE_FORMAT)
	private LocalDate dataSorteio;
	
	@JsonProperty("dezenasSorteadasOrdemSorteio")
	private List<Integer> numerosSorteadosOrdemSorteio;

	@JsonProperty("listaDezenas")
	private List<Integer> numerosSorteados;
	
}
