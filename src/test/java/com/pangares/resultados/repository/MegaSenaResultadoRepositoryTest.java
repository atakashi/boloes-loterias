package com.pangares.resultados.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.pangares.resultados.entities.MegaSenaResultadoEntity;
import com.pangares.resultados.helper.MegaSenaMocksHelpers;

@DataJpaTest
public class MegaSenaResultadoRepositoryTest {

	@Autowired
	private MegaSenaResultadoRepository repository;
	
	@Test
	public void getLastNumeroConcursoTest() {
		
		// Without concursos in database
		Long withoutNumeroConcurso = this.repository.getLastNumeroConcurso();
		assertNull(withoutNumeroConcurso);
		
		
		// Only one concurso
	    MegaSenaResultadoEntity concurso576 = MegaSenaMocksHelpers.buildEntity576();
		this.repository.save(concurso576);
		
		Long newNumeroConcurso = this.repository.getLastNumeroConcurso(); 
		assertEquals(concurso576.getNumeroConcurso(), newNumeroConcurso);

		
		// The new concurso saved is 'before'
	    MegaSenaResultadoEntity concurso99 = MegaSenaMocksHelpers.buildEntity99();
		this.repository.save(concurso99);
		
		Long beforeNumeroConcurso = this.repository.getLastNumeroConcurso(); 
		assertEquals(concurso576.getNumeroConcurso(), newNumeroConcurso);
		
		
		// The new concurso saved is the 'last'
	    MegaSenaResultadoEntity concurso1584 = MegaSenaMocksHelpers.buildEntity1584();
		this.repository.save(concurso1584);
		
		Long lastNumeroConcurso = this.repository.getLastNumeroConcurso(); 
		assertEquals(concurso1584.getNumeroConcurso(), lastNumeroConcurso);
	}
	
}
