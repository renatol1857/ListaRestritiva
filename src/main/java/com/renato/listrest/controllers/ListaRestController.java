package com.renato.listrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.ListaRestDTO;
import com.renato.listrest.models.services.ListaRestService;

@RestController
@RequestMapping(path = "/restritiva")
public class ListaRestController {
	@Autowired
	ListaRestService service;

	@PostMapping(path = "/{fullfone}")
	public ListaRestDTO savar(@PathVariable String fullfone) {
		return ListaRestDTO.transfonaEmDTO(service.save(fullfone));
	}
	
	@PostMapping
	public ListaRestDTO savar(String ddi, String ddd, String fone ) {
		return ListaRestDTO.transfonaEmDTO(service.save(ddi,ddd,fone));
	}

	@GetMapping(path = "/{fullfone}")
	public ListaRestDTO consultarFullFone(@PathVariable String fullfone) {
		return null; //service.consultarFullFone(fullfone);
	}
	
	// @GetMapping
	public ListaRestDTO consultar(String ddi, String ddd, String fone) {
		return null; //service.save(ddi, ddd, fone);
	}
	
	@GetMapping
	public List<ListaRestDTO> findByAll() {
		return service.findAll();
	}
	
}
