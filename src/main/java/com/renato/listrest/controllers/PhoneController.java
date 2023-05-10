package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.PhoneRespostaDTO;
import com.renato.listrest.models.services.PhoneService;

@RestController
@RequestMapping(path = "/fone")
public class PhoneController {
	@Autowired
	private PhoneService service;

	@PostMapping(path = "/{mcdu}/{fullfone}")
	public PhoneRespostaDTO save(@PathVariable String mcdu, @PathVariable String fullfone) {
		return service.save(mcdu, fullfone);
	}
	
	@PostMapping(path = "/{mcdu}")
	public PhoneRespostaDTO consultarFone(@PathVariable String mcdu, String ddi, String ddd, String fone) {
		return service.save(mcdu, ddi, ddd, fone);
	}
	
	@GetMapping(path = "/{mcdu}/{fullfone}")
	public PhoneRespostaDTO consultar(@PathVariable String mcdu, @PathVariable String fullfone) {
		return service.consultarFone(mcdu, fullfone);
	}
	
}
