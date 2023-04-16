package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.services.DNISService;

@RestController
@RequestMapping(path = "/dnis")
public class DNISController {
	@Autowired
	private DNISService dnisService;
	
	@PostMapping
	public DNIS save(DNIS dnis) {
		return dnisService.save(dnis);
	}
}
