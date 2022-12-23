package com.pangares.resultados.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "megasena")
@Builder
@Getter
public class MegaSenaResultadoEntity extends AbstractResultadoEntity {

	@Column(nullable = false, unique = true)
	protected Long numeroConcurso;

	@Column
	protected LocalDate dataSorteio;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column
	private List<Integer> numerosSorteadosOrdemSorteio;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column
	private Set<Integer> numerosSorteados;

}
