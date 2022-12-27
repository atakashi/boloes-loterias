package com.pangares.resultados.batch;

import com.pangares.resultados.dto.MegaSenaResultadoDto;
import com.pangares.resultados.entities.Quadrante;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class MegaSenaResultadoItemProcessor<I extends MegaSenaResultadoDto, O extends Quadrante> implements ItemProcessor<I, O> {
	 
	@Override
	public Quadrante process(MegaSenaResultadoDto item) throws Exception {
		
//		Set<Integer> setNumerosSorteados = Set.<Integer>copyOf(item.getNumerosSorteados());
//		String chaveNumerosSorteados = ResultadosUtil.generateChaveNumerosSorteados(item);

		Quadrante quadrante =  Quadrante.builder()
				.sorteio(item.getNumeroConcurso())
				.dataSorteio(item.getDataSorteio())
				.primeiroNumero(item.getNumerosSorteadosOrdemSorteio().get(0))
				.segundoNumero(item.getNumerosSorteadosOrdemSorteio().get(1))
				.terceiroNumero(item.getNumerosSorteadosOrdemSorteio().get(2))
				.quartoNumero(item.getNumerosSorteadosOrdemSorteio().get(3))
				.quintoNumero(item.getNumerosSorteadosOrdemSorteio().get(4))
				.sextoNumero(item.getNumerosSorteadosOrdemSorteio().get(5))
				.build();
		
//		MegaSenaResultadoEntity entity = MegaSenaResultadoEntity.builder()
//				.numeroConcurso(item.getNumeroConcurso())
//				.dataSorteio(item.getDataSorteio())
//				.numerosSorteadosOrdemSorteio(item.getNumerosSorteadosOrdemSorteio())
//				.numerosSorteados(setNumerosSorteados)
//				.chaveNumerosSorteados(chaveNumerosSorteados)
//				.build();
		
		log.info("Entity processed: " + quadrante);
		return quadrante;
	}

}
