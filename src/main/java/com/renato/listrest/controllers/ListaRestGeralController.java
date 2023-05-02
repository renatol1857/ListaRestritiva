package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.ListaRestGeralDTO;
import com.renato.listrest.models.services.ListaRestGeralService;

@RestController
@RequestMapping(path = "/restritiva")
public class ListaRestGeralController {
	@Autowired
	ListaRestGeralService restService;

	@PostMapping(path = "/full")
	public ListaRestGeralDTO savar(String fullfone) {
		return ListaRestGeralDTO.transfonaEmDTO(restService.save(fullfone));
	}
	
	@PostMapping
	public ListaRestGeralDTO savar(String ddi, String ddd, String fone ) {
		return ListaRestGeralDTO.transfonaEmDTO(restService.save(ddi,ddd,fone));
	}

	
	
}
