package com.pangares.resultados.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.pangares.resultados.entities.AbstractResultadoEntity;

@NoRepositoryBean
public interface ResultadoRepository<T extends AbstractResultadoEntity, ID> extends JpaRepository<T, ID> {

	T findByNumeroConcurso(Long numeroConcurso);
	
	T findByDataSorteio(LocalDate dataSorteio);

}
