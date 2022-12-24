package com.pangares.resultados.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.pangares.resultados.dto.MegaSenaResultadoDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MegaSenaResultadoEndpoint extends AbstractResultadosEndpoint {
	
	@Value(value = "${caixa.resultados.api.endpoint.megasena}")
	private String RESULTADO_ENDPOINT;
	
	@Autowired
	public MegaSenaResultadoEndpoint(RestTemplate restTemplate) {
		super(restTemplate);
	}
	
	
	public MegaSenaResultadoDto getResultado(long numeroConcurso) throws RestClientException {
		log.info("numeroConcurso: " + numeroConcurso);

		ResponseEntity<MegaSenaResultadoDto> response = restTemplate.getForEntity(
				BASE_URL + String.format(RESULTADO_ENDPOINT, numeroConcurso), 
				MegaSenaResultadoDto.class);
		
		if (HttpStatus.OK.equals(response.getStatusCode())) {

			log.info("response: " + response.toString());
			return response.getBody();
		} else {

			log.info("response: " + response.toString());
			throw new RestClientException("[Error] status code: " + response.getStatusCode());
		}
		
	}
	
}
