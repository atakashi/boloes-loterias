package com.pangares.resultados.batch;

import java.util.Collection;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;

import com.pangares.resultados.dto.MegaSenaResultadoDto;
import com.pangares.resultados.entities.MegaSenaResultadoEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MegaSenaResultadoItemProcessor<I extends MegaSenaResultadoDto, O extends MegaSenaResultadoEntity> implements ItemProcessor<I, O> {
	 
	@Override
	public MegaSenaResultadoEntity process(MegaSenaResultadoDto item) throws Exception {
		
		Set<Integer> setNumerosSorteados = Set.<Integer>copyOf(item.getNumerosSorteados());
		String chaveNumerosSorteados = this.generateChaveNumerosSorteados(setNumerosSorteados);
		
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

	/**
	 * Gera um valor <i>hash</i> para ser usado "<strong>técnica heurística</strong>" para descobrir se um jogo já foi
	 * sorteado anteriormente.
	 * 
	 * @deprecated Trocar posterior por uma estratégica melhor, como "Bloom Filter":
	 * https://pt.stackoverflow.com/questions/359081/o-que-é-e-para-que-serve-um-bloom-filter
	 * 
	 * @param item
	 * @return
	 */
	@Deprecated
	private String generateChaveNumerosSorteados(Collection<Integer> colNumerosSorteados) {
		final int intHashCode = colNumerosSorteados.hashCode();
		return String.valueOf(intHashCode);
	}
	
}
