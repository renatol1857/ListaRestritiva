package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.ListaFreeGeralTDO;
import com.renato.listrest.models.services.ListaFreeGeralService;

@RestController
@RequestMapping(path = "/lstfree")
public class ListaFreeGeralController {
	@Autowired
	ListaFreeGeralService objService;
	
	
	@PostMapping(path = "/{fullfone}")
	public ListaFreeGeralTDO savar(@PathVariable String fullfone) {
		return ListaFreeGeralTDO.transfonaEmDTO(objService.save(fullfone));
	}
	
	@PostMapping
	public ListaFreeGeralTDO savar(String ddi, String ddd, String fone ) {
		return ListaFreeGeralTDO.transfonaEmDTO(objService.save(ddi,ddd,fone));
	}
	
	
}
