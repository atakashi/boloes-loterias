package com.pangares.resultados.helper;

import java.time.LocalDate;
import java.util.Arrays;

import com.pangares.resultados.dto.MegaSenaResultadoDto;

public class MegaSenaMocksHelpers {
	
	public static MegaSenaResultadoDto buildDto576() {
		return MegaSenaResultadoDto.builder()
				.numeroConcurso(Long.valueOf(576))
				.dataSorteio(LocalDate.of(2004, 06, 30))
				.numerosSorteadasOrdemSorteio(Arrays.<Integer>asList(18, 43, 52, 39, 7, 8))
				.numerosSorteados(Arrays.<Integer>asList(7, 8, 18, 39, 43, 52))
				.build();
	}
}
