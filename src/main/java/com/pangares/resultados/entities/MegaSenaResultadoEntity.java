package com.pangares.resultados.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "megasena")
@Builder
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true, exclude = {"id"}, callSuper = false)
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

	/**
	 * Campo para pesquisa heurística, para acelerar a pesquisa de jogos já sorteados
	 */
	@Column
	private String chaveNumerosSorteados;

}
