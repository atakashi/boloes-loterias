package com.pangares.estatisticas.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.pangares.estatisticas.dtos.EstatisticasDto;
import com.pangares.estatisticas.dtos.FrequenciaDto;
import com.pangares.estatisticas.dtos.NumeroFrequenciaDto;
import com.pangares.estatisticas.dtos.ParImparDto;
import com.pangares.estatisticas.dtos.QuadranteDto;
import com.pangares.estatisticas.dtos.QuadranteEnum;
import com.pangares.estatisticas.util.NumeroFrequenciaDtoComparator;
import com.pangares.resultados.entities.MegaSenaResultadoEntity;
import com.pangares.resultados.repository.MegaSenaResultadoRepository;

@Service
public class EstatisticasService {

	@Autowired
	private MegaSenaResultadoRepository resultadoRepository;
	
	public EstatisticasDto getMegaSenaEstatisticas() {
		
		List<MegaSenaResultadoEntity> resultados = this.resultadoRepository.findAll(Sort.by("numeroConcurso"));
		
		return EstatisticasDto.builder()
				.frequencias(this.buildNumeroFrequencias(resultados))
				.parImpar(this.parImpar(resultados))
				.quadrante(this.quadrante(resultados))
				.build();
	}
	
	/**
	 * Porcentagem e quantidade dos números sorteados
	 * @param resultados
	 * @return
	 */
	public List<NumeroFrequenciaDto> buildNumeroFrequencias(List<MegaSenaResultadoEntity> resultados) {
		Map<Integer, Integer> qtdOcorrenciasMap = new HashMap<>();
		for (MegaSenaResultadoEntity resultado : resultados) {
			for (Integer numeroSorteado : resultado.getNumerosSorteados()) {
				
				int qtdOcorrencias = qtdOcorrenciasMap.containsKey(numeroSorteado)
						? qtdOcorrenciasMap.get(numeroSorteado) + 1
						: 1;
				
				qtdOcorrenciasMap.put(numeroSorteado, qtdOcorrencias);
			}
		}
		
		//
		List<NumeroFrequenciaDto> frequencias = new ArrayList<>();
		for (Integer numeroSorteado : qtdOcorrenciasMap.keySet()) {
			Integer qtdOcorrencias = qtdOcorrenciasMap.get(numeroSorteado);
			
			NumeroFrequenciaDto frequencia = NumeroFrequenciaDto.builder()
					.numero(numeroSorteado)
					.quantidadeOcorrencias(qtdOcorrencias)
					.quadrante(QuadranteEnum.qualQuadrante(numeroSorteado))
					.build();
			frequencias.add(frequencia);
		}

		frequencias.sort(NumeroFrequenciaDtoComparator.build(false));

		return frequencias;
	}
	
	/**
	 * Qtd de pares impares
	 * @param resultados
	 * @return
	 */
	public ParImparDto parImpar(List<MegaSenaResultadoEntity> resultados) {
		
		int par = 0;
		int impar = 0;
		for (MegaSenaResultadoEntity resultado : resultados) {
			for (Integer numeroSorteado : resultado.getNumerosSorteados()) {
				
				if (numeroSorteado % 2 == 0) {
					par++;
				} else {
					impar++;
				}
			}
		}
		
		return ParImparDto.builder()
				.par(FrequenciaDto.builder()
						.quantidadeOcorrencias(par)
						.build())
				.impar(FrequenciaDto.builder()
						.quantidadeOcorrencias(impar)
						.build())
				.build();
	}
	
	/**
	 * Qtd de pares impares
	 * @param resultados
	 * @return
	 */
	public QuadranteDto quadrante(List<MegaSenaResultadoEntity> resultados) {

		Map<QuadranteEnum, Integer> quadrantesMap = new HashMap<>();
		for (MegaSenaResultadoEntity resultado : resultados) {
			for (Integer numeroSorteado : resultado.getNumerosSorteados()) {
				
				QuadranteEnum quadranteEnum = QuadranteEnum.qualQuadrante(numeroSorteado); 
				int qtdOcorrencias = quadrantesMap.containsKey(quadranteEnum)
						? quadrantesMap.get(quadranteEnum) + 1
						: 1;

				quadrantesMap.put(quadranteEnum, qtdOcorrencias);
			}
		}
		
		return QuadranteDto.builder()
				.quadrante1(FrequenciaDto.builder()
						.quantidadeOcorrencias(quadrantesMap.get(QuadranteEnum.QUADRANTE_1))
						.build())
				.quadrante2(FrequenciaDto.builder()
						.quantidadeOcorrencias(quadrantesMap.get(QuadranteEnum.QUADRANTE_2))
						.build())
				.quadrante3(FrequenciaDto.builder()
						.quantidadeOcorrencias(quadrantesMap.get(QuadranteEnum.QUADRANTE_3))
						.build())
				.quadrante4(FrequenciaDto.builder()
						.quantidadeOcorrencias(quadrantesMap.get(QuadranteEnum.QUADRANTE_4))
						.build())
				.build();
	}
	
}
