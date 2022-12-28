package com.pangares.resultados.repository;

import com.pangares.resultados.entities.Quadrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuadranteRepository extends JpaRepository<Quadrante, Long> {

}
