package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	private DNISService service;

	@PostMapping
	public DNIS save(@Valid DNISDTO dnisDTO) {
		return service.save(dnisDTO.transformaToObj());
	}

	@PutMapping
	public DNIS update(@Valid DNISPutDTO dnisPutDTO) {
		return service.update(dnisPutDTO.transformaToObj());
	}

	@DeleteMapping("/{mcdu}")
	public ResponseEntity<DNIS> apagar(@PathVariable String mcdu) {
		return service.deleteCascade(mcdu);
	}

	@GetMapping("/{mcdu}")
	public DNIS findByDnis(@PathVariable String mcdu) {
		return service.findByDnis(mcdu);
	}

	@GetMapping
	public Iterable<DNIS> findAll() {
		return service.findAll();
	}

	@GetMapping("pagina/{numPag}")
	public Iterable<DNIS> findAll(@PathVariable int numPag) {
		return service.findAll(numPag);
	}

}
