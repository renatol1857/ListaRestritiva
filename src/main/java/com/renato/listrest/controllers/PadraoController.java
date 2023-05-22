package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.PadraoDTO;
import com.renato.listrest.models.entities.Padrao;
import com.renato.listrest.models.services.PadraoService;

@RestController
@RequestMapping(path = "/padrao")
public class PadraoController {

	@Autowired
	private PadraoService service;
	
	@PostMapping
	public ResponseEntity<Padrao> savar(PadraoDTO padraoDTO) {
		return service.save(PadraoDTO.transfonaEmObj(padraoDTO));
	}
	
	@GetMapping(path = "/{num_pag}")
	public Iterable<Padrao> findAll(@PathVariable(name = "num_pag") int numPage){
		
		return null;
	}
	
	
}
