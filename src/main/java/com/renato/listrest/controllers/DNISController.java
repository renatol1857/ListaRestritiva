package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.DNISDTO;
import com.renato.listrest.models.dto.DNISPutDTO;
import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.services.DNISService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/dnis")
public class DNISController {
	@Autowired
	private DNISService dnisService;

	@PostMapping
	public DNIS save(@Valid DNISDTO dnisDTO) {
		return dnisService.save(dnisDTO.transformaToObj());
	}

	@PutMapping
	public DNIS update(@Valid DNISPutDTO dnisPutDTO) {
		return dnisService.update(dnisPutDTO.transformaToObj());
	}

	@GetMapping("/{sDnis}")
	public DNIS findByDnis(@PathVariable String sDnis) {
		return dnisService.findByDnis(sDnis);
	}

	@GetMapping("")
	public Iterable<DNIS> findByAll() {
		return dnisService.findAll();
	}
	
}
