package com.renato.listrest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.ListaRestGeralDTO;
import com.renato.listrest.models.services.ListaRestGeralService;

@RestController
@RequestMapping(path = "/restritiva")
public class ListaRestGeralController {
	@Autowired
	ListaRestGeralService service;

	@PostMapping(path = "/{fullfone}")
	public ListaRestGeralDTO savar(@PathVariable String fullfone) {
		return ListaRestGeralDTO.transfonaEmDTO(service.save(fullfone));
	}
	
	@PostMapping
	public ListaRestGeralDTO savar(String ddi, String ddd, String fone ) {
		return ListaRestGeralDTO.transfonaEmDTO(service.save(ddi,ddd,fone));
	}

	@GetMapping(path = "/{fullfone}")
	public ListaRestGeralDTO consultarFullFone(@PathVariable String fullfone) {
		return null; //service.consultarFullFone(fullfone);
	}
	
	// @GetMapping
	public ListaRestGeralDTO consultar(String ddi, String ddd, String fone) {
		return null; //service.save(ddi, ddd, fone);
	}
	
	@GetMapping
	public List<ListaRestGeralDTO> findByAll() {
		return service.findAll();
	}
	
}
