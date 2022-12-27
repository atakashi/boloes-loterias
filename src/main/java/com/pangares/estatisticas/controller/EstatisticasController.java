package com.pangares.estatisticas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pangares.estatisticas.dtos.EstatisticasDto;
import com.pangares.estatisticas.service.EstatisticasService;

@RestController
@RequestMapping("/api/estatisticas")
public class EstatisticasController {

	@Autowired
	private EstatisticasService service;
	
	@GetMapping
	public EstatisticasDto getEstatisticas(Model model) {
		EstatisticasDto dto = this.service.getMegaSenaEstatisticas();
		model.addAttribute("estatisticas", dto);
		
		return dto;
	}
}
