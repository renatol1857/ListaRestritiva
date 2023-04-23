package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.DNISDTO;
import com.renato.listrest.models.dto.PhoneSalvarRespostaDTO;
import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.services.DNISService;
import com.renato.listrest.models.services.PhoneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/dnis")
public class DNISController {
	@Autowired
	private DNISService dnisService;
	@Autowired
	private PhoneService phoneService;
	
	@PostMapping
	public DNIS save(@Valid DNISDTO dnisDTO) {
		return dnisService.save(dnisDTO.transformaToObj());
	}
	
	@GetMapping("/{id}")
	public DNIS findByID(@PathVariable Long id) {
		return dnisService.findByID(id);
	}
	
	@PostMapping(path = "/fone/{dnis}")
	public PhoneSalvarRespostaDTO save(@PathVariable String dnis, String fullfone) {
		return phoneService.save(dnis, fullfone);
	}
	
	
}
