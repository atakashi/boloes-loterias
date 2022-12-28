package com.pangares.resultados.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "quadrantes")
@Builder
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(doNotUseGetters = true, exclude = {"id"}, callSuper = false)
public class Quadrante extends AbstractResultadoEntity {

    @Column(name = "sorteio")
    private long sorteio;

    @Column(name = "data_sorteio")
    private LocalDate dataSorteio;
    @Column(name = "primeiro_numero")
    private int primeiroNumero;
    @Column(name = "segundo_numero")
    private int segundoNumero;
    @Column(name = "terceiro_numero")
    private int terceiroNumero;
    @Column(name = "quarto_numero")
    private int quartoNumero;
    @Column(name = "quinto_numero")
    private int quintoNumero;
    @Column(name = "sexto_numero")
    private int sextoNumero;
}
