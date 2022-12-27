package com.pangares.surpresinha.controller.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pangares.estatisticas.util.NumeroFrequenciaDtoComparator;
import com.pangares.resultados.entities.MegaSenaResultadoEntity;
import com.pangares.resultados.repository.MegaSenaResultadoRepository;
import com.pangares.resultados.util.ResultadosUtil;

@Service
public class SupresinhaService {

    @Autowired
    private MegaSenaResultadoRepository repository;


    public List<Integer> generateSurpresinhaNaoSorteada(int qtdDezenas, boolean balanceaQuadrante) {

        List<Integer> surpresinha = new ArrayList<>();
        boolean jaFoiSorteado = true;
        do {

			/*
		
		int qtdNumeros = Math.max(6, qtdDezenas);
		for (int i = 0; i < qtdNumeros; i++) {
			surpresinha.add(this.generateMegaSenaRandon());
		}

		
		// Validacoes:
		
		if (balanceaQuadrante) {
			
		}
		
		
		
		
		// NumeroFrequenciaDtoComparator.build(true)
			 */


        } while (jaFoiSorteado);

        return surpresinha;
    }

    /**
     * Gera um numero aleatorio entre 1 e 60
     *
     * @return
     */
    public Integer generateMegaSenaRandon() {
        final int min = 1;
        final int max = 60;
        final int range = max - min + 1;

        return (int) (Math.random() * range) + min;
    }

    public boolean jaFoiSorteado(List<Integer> surpresinha) {

        final int jogoPadrao = 6;
        final int qtdDezenas = surpresinha.size();
        final int qtdCombinacoes = surpresinha.size() - qtdDezenas;
//		int i = 0;


        for (int i = 0; (i + 5) < surpresinha.size(); i++) {
        }


        // qtdDezenas = 6
        List<Integer> surpresinhaTeste = surpresinha.subList(
                0,    // i = 0
                5);    // qtdDezenas  - 1
        surpresinhaTeste.sort(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.intValue() > o2.intValue()) {
                    return 1;
                } else if (o1.intValue() < o2.intValue()) {
                    return -1;
                }
                return 0;
            }
        });

        boolean jogoJaSorteado = false;
        String hash = ResultadosUtil.generateChaveNumerosSorteados(surpresinhaTeste);
        List<MegaSenaResultadoEntity> entities = this.repository.findByChaveNumerosSorteados(hash);
        if (entities != null && !entities.isEmpty()) {
            Set<Integer> j = new HashSet<>(surpresinhaTeste);
            for (MegaSenaResultadoEntity jogoSorteado : entities) {
                if (j.equals(jogoSorteado.getNumerosSorteados())) {
                    jogoJaSorteado = true;
                    break;
                }
            }
        }

        return jogoJaSorteado;


//		do {
//			List<Integer> surpresinhaTeste = surpresinha.subList(i, ???);
//			surpresinhaTeste.sort(NumeroFrequenciaDtoComparator.build(true));
//			
//			i++;
//		} while(i < ??);


    }

}
