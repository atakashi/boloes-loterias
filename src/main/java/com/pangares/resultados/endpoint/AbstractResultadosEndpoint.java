package com.pangares.resultados.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractResultadosEndpoint {
	
	@Value(value = "${caixa.resultados.api.base.url}")
	protected String BASE_URL;
	
	protected RestTemplate restTemplate;

	public AbstractResultadosEndpoint(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
