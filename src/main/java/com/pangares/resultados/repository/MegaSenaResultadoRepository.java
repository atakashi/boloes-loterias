package com.pangares.resultados.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pangares.resultados.entities.MegaSenaResultadoEntity;

@Repository
public interface MegaSenaResultadoRepository extends ResultadoRepository<MegaSenaResultadoEntity, Long> {

	@Query("select max(r.numeroConcurso) from MegaSenaResultadoEntity r")
	public Long getLastNumeroConcurso();
	
	public List<MegaSenaResultadoEntity> findByChaveNumerosSorteados(String chaveNumerosSorteados);
	
	public List<MegaSenaResultadoEntity> findByNumerosSorteados(Set<Integer> numerosSorteados);
}
