package com.renato.listrest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NoArgsConstructor;

@RestController
@NoArgsConstructor
public class Teste {
	
	@GetMapping("/dniservice")
	public String dniService() {
		return "dniService";
	}
	
	


}
