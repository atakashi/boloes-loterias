package com.pangares.resultados.util;

import java.security.MessageDigest;
import java.util.Collection;

import com.pangares.resultados.dto.MegaSenaResultadoDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultadosUtil {

	/**
	 * Gera um valor <i>hash</i> para ser usado "<strong>técnica heurística</strong>" para descobrir se um jogo já foi
	 * sorteado anteriormente.
	 * 
	 * TODO: Melhorar o uso do Bloom filter:
	 * https://pt.stackoverflow.com/questions/359081/o-que-é-e-para-que-serve-um-bloom-filter
	 * 
	 * @deprecated Pensar melhor ,e transformar em um <i>bean</i>
	 * 
	 * @param item
	 * @return
	 */
	@Deprecated
	public static String generateChaveNumerosSorteados(MegaSenaResultadoDto dto) {
		
		Collection<Integer> numerosSorteados = dto.getNumerosSorteados();
		
		try {
			StringBuilder strBuilder = new StringBuilder();
			for (Integer i : numerosSorteados) {
				strBuilder.append(i.toString());
				strBuilder.append(";");
			}
			
			MessageDigest algorith = MessageDigest.getInstance("SHA-256");
			byte[] hash = algorith.digest(strBuilder.toString().getBytes());
			
			StringBuilder key = new StringBuilder();
			for (byte b : hash) {
				key.append(String.format("%02X", 0xFF & b));
			}
			
			return key.toString();
		} catch (Exception e) {
			log.error("MegaSena don't has a valid result: " + dto);
			return "0000000000000000000000000000000000000000000000000000000000000000";
		}
		
	}
	
}
