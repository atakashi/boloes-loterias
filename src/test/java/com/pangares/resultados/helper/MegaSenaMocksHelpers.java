package com.pangares.resultados.helper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

import com.pangares.resultados.dto.MegaSenaResultadoDto;
import com.pangares.resultados.entities.MegaSenaResultadoEntity;

public class MegaSenaMocksHelpers {
	
	public static MegaSenaResultadoEntity buildEntity99() {
		return MegaSenaResultadoEntity.builder()
				.numeroConcurso(Long.valueOf(99))
				.dataSorteio(LocalDate.of(1998, 01, 25))
				.numerosSorteadosOrdemSorteio(Arrays.<Integer>asList(30, 33, 24, 11, 46, 60))
				.numerosSorteados(Set.<Integer>of(11, 24, 30, 33, 46, 60))
				.build();
	}
	
	public static MegaSenaResultadoEntity buildEntity576() {
		return MegaSenaResultadoEntity.builder()
				.numeroConcurso(Long.valueOf(576))
				.dataSorteio(LocalDate.of(2004, 06, 30))
				.numerosSorteadosOrdemSorteio(Arrays.<Integer>asList(18, 43, 52, 39, 7, 8))
				.numerosSorteados(Set.<Integer>of(18, 43, 52, 39, 7, 8))
				.build();
	}

	public static MegaSenaResultadoEntity buildEntity1584() {
		return MegaSenaResultadoEntity.builder()
				.numeroConcurso(Long.valueOf(1584))
				.dataSorteio(LocalDate.of(2014, 3, 22))
				.numerosSorteadosOrdemSorteio(Arrays.<Integer>asList(27, 54, 28, 50, 22, 03))
				.numerosSorteados(Set.<Integer>of(03, 22, 27, 28, 50, 54))
				.build();
	}
	
	public static MegaSenaResultadoDto buildDto576() {
		return MegaSenaResultadoDto.builder()
				.numeroConcurso(Long.valueOf(576))
				.dataSorteio(LocalDate.of(2004, 06, 30))
				.numerosSorteadosOrdemSorteio(Arrays.<Integer>asList(18, 43, 52, 39, 7, 8))
				.numerosSorteados(Arrays.<Integer>asList(7, 8, 18, 39, 43, 52))
				.build();
	}
}
