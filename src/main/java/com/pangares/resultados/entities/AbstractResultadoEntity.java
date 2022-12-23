package com.pangares.resultados.entities;

import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class AbstractResultadoEntity extends AbstractPersistable<Long> {

}
