package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.FreeHistDTO;
import com.renato.listrest.models.dto.FreeDTO;
import com.renato.listrest.models.entities.Free;
import com.renato.listrest.models.services.FreeService;

@RestController
@RequestMapping(path = "/free")
public class FreeController {
	@Autowired
	FreeService service;

	@PostMapping(path = "/{fullPhone}")
	public ResponseEntity<FreeDTO> savar(@PathVariable String fullPhone) {
		ResponseEntity<Free> resp = service.save(fullPhone);
		return ResponseEntity.status(resp.getStatusCode()).body(FreeDTO.transfonaEmDTO(resp.getBody()));
	}

	@PostMapping
	public ResponseEntity<FreeDTO> savar(String ddi, String ddd, String fone) {
		ResponseEntity<Free> resp = service.save(ddi, ddd, fone);
		return ResponseEntity.status(resp.getStatusCode()).body(FreeDTO.transfonaEmDTO(resp.getBody()));
	}

	@GetMapping(path = "/{fullPhone}")
	public FreeDTO consultar(@PathVariable String fullPhone) {
		return FreeDTO.transfonaEmDTO(service.consultar(fullPhone));
	}
	
	@PutMapping(path = "/{fullPhone}")
	public FreeDTO consultarInc(@PathVariable String fullPhone) {
		return FreeDTO.transfonaEmDTO(service.consultarInc(fullPhone));
	}
	
	/* #FIXME preciso ajustar a paginacao do historico */
	@GetMapping(path = "/historico/{fullPhone}/{num_pag}")
	public FreeHistDTO consultarHistorico(@PathVariable String fullPhone, @PathVariable String num_pag) {
		return service.consultarHistorico(fullPhone, num_pag);
	}

	@GetMapping(path = "/listar/{num_pag}")
	public Iterable<Free> listar(@PathVariable int num_pag) {
		return service.findAll(num_pag);
	}
	
	@DeleteMapping(path = "/apagar/{fullPhone}")
	public ResponseEntity<String> apagar(@PathVariable String fullPhone) {
		service.apagar(fullPhone);
		return ResponseEntity.status(HttpStatus.OK).body(String.format("Fone %s e seus históricos foram apagados com sucesso",fullPhone) );
	}
	
}
