package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.errors.ResponseGeralRL;
import com.renato.listrest.models.services.EstaticaService;

@RestController
@RequestMapping(path = "/estatica")
public class EstaticaController {
	@Autowired
	EstaticaService service;

	@GetMapping("/{fullPhone}")
	public ResponseEntity<ResponseGeralRL> consultar(@PathVariable String fullPhone) {
		return service.consultar(fullPhone);
	}

	@GetMapping("/like/{phone}")
	public ResponseEntity<String> consultarLike(@PathVariable String phone) {

		return null;
	}

	@PutMapping("/{fullPhone}")
	public ResponseEntity<String> consultarInc(@PathVariable String fullPhone) {

		return null;
	}

}
