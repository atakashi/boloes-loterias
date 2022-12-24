package com.pangares.resultados.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.pangares.resultados.repository.MegaSenaResultadoRepository;

@SpringBootTest
public class MegaSenaResultadoServiceTest {

	@InjectMocks
	private MegaSenaResultadoService service;
	
	@Mock
	private MegaSenaResultadoRepository repository;
	
	private final Long ZERO = Long.valueOf(0);
	
	@Test
	public void getLastNumeroConcursoTest() {
		
		// Database is empty
		Mockito.when(repository.getLastNumeroConcurso()).thenReturn(null);
		Long numeroConcursoIsZero = this.service.getLastNumeroConcurso();
		assertEquals(ZERO, numeroConcursoIsZero);

		// Database with wrong entity
		Mockito.when(repository.getLastNumeroConcurso()).thenReturn(Long.valueOf(-1));
		Long numeroConcursoIsZeroToo = this.service.getLastNumeroConcurso();
		assertEquals(ZERO, numeroConcursoIsZeroToo);
	
		// Database with right value
		Mockito.when(repository.getLastNumeroConcurso()).thenReturn(Long.valueOf(10));
		Long numeroConcursoIsValid = this.service.getLastNumeroConcurso();
		assertTrue(ZERO < numeroConcursoIsValid);
	}
	
	
}
