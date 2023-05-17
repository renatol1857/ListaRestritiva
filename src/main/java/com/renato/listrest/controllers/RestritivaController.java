package com.renato.listrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.RestritivaDTO;
import com.renato.listrest.models.services.RestritivaService;

@RestController
@RequestMapping(path = "/restritiva")
public class RestritivaController {
	@Autowired
	RestritivaService service;

	@PostMapping(path = "/{fullfone}")
	public RestritivaDTO savar(@PathVariable String fullfone) {
		return RestritivaDTO.transfonaEmDTO(service.save(fullfone));
	}
	
	@PostMapping
	public RestritivaDTO savar(String ddi, String ddd, String fone ) {
		return RestritivaDTO.transfonaEmDTO(service.save(ddi,ddd,fone));
	}

	@GetMapping(path = "/{fullfone}")
	public RestritivaDTO consultarFullFone(@PathVariable String fullfone) {
		return null; //service.consultarFullFone(fullfone);
	}
	
	// @GetMapping
	public RestritivaDTO consultar(String ddi, String ddd, String fone) {
		return null; //service.save(ddi, ddd, fone);
	}
	
	@GetMapping
	public List<RestritivaDTO> findByAll() {
		return service.findAll();
	}
	
}
