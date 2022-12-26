package com.pangares.resultados.batch;

import java.util.Set;

import org.springframework.batch.item.ItemProcessor;

import com.pangares.resultados.dto.MegaSenaResultadoDto;
import com.pangares.resultados.entities.MegaSenaResultadoEntity;
import com.pangares.resultados.util.ResultadosUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MegaSenaResultadoItemProcessor<I extends MegaSenaResultadoDto, O extends MegaSenaResultadoEntity> implements ItemProcessor<I, O> {
	 
	@Override
	public MegaSenaResultadoEntity process(MegaSenaResultadoDto item) throws Exception {
		
		Set<Integer> setNumerosSorteados = Set.<Integer>copyOf(item.getNumerosSorteados());
		String chaveNumerosSorteados = ResultadosUtil.generateChaveNumerosSorteados(item);
		
		MegaSenaResultadoEntity entity = MegaSenaResultadoEntity.builder()
				.numeroConcurso(item.getNumeroConcurso())
				.dataSorteio(item.getDataSorteio())
				.numerosSorteadosOrdemSorteio(item.getNumerosSorteadosOrdemSorteio())
				.numerosSorteados(setNumerosSorteados)
				.chaveNumerosSorteados(chaveNumerosSorteados)
				.build();
		
		log.info("Entity processed: " + entity);
		return entity;
	}

}
