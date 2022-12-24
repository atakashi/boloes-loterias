package com.pangares.resultados.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pangares.resultados.endpoint.MegaSenaResultadoEndpoint;
import com.pangares.resultados.repository.MegaSenaResultadoRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MegaSenaResultadoService {

	private MegaSenaResultadoEndpoint endpoint;
	
	private MegaSenaResultadoRepository repository;
	
	private final Long ZERO = Long.valueOf(0);
	
	@Autowired
	public MegaSenaResultadoService(MegaSenaResultadoEndpoint endpoint, MegaSenaResultadoRepository repository) {
		this.endpoint = endpoint;
		this.repository = repository;
	}

	public Long getLastNumeroConcurso() {
		Long _lastNumeroConcurso = this.repository.getLastNumeroConcurso();
		log.info("lastNumeroConcurso: " + _lastNumeroConcurso);
		
		return (_lastNumeroConcurso == null || _lastNumeroConcurso < 0) 
				? ZERO 
				: _lastNumeroConcurso;
	}
	
	
}
