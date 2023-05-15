package com.renato.listrest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.listrest.models.dto.ListaFreeTDO;
import com.renato.listrest.models.entities.ListaFree;
import com.renato.listrest.models.services.ListaFreeService;

@RestController
@RequestMapping(path = "/free")
public class ListaFreeController {
	@Autowired
	ListaFreeService service;

	@PostMapping(path = "/{fullPhone}")
	public ResponseEntity<ListaFreeTDO> savar(@PathVariable String fullPhone) {
		ResponseEntity<ListaFree> resp = service.save(fullPhone);
		ListaFree lst = resp.getBody();
		return ResponseEntity.status(resp.getStatusCode()).body(ListaFreeTDO.transfonaEmDTO(lst));
	}

	@PostMapping
	public ResponseEntity<ListaFreeTDO> savar(String ddi, String ddd, String fone) {
		ResponseEntity<ListaFree> resp = service.save(ddi, ddd, fone);
		ListaFree lst = resp.getBody();
		return ResponseEntity.status(resp.getStatusCode()).body(ListaFreeTDO.transfonaEmDTO(lst));
	}

	@GetMapping(path = "/{fullPhone}")
	public ListaFreeTDO consultar(@PathVariable String fullPhone) {
		return ListaFreeTDO.transfonaEmDTO(service.consultar(fullPhone));
	}
	
	@PutMapping(path = "/{fullPhone}")
	public ListaFreeTDO consultarInc(@PathVariable String fullPhone) {
		return ListaFreeTDO.transfonaEmDTO(service.consultarInc(fullPhone));
	}
	
	/* #FIXME preciso ajustar consultarHistorico - funcionamento parcial */
	@GetMapping(path = "/historico/{fullPhone}/{num_pag}")
	public ListaFree consultarHistorico(@PathVariable String fullPhone, @PathVariable String num_pag) {
		return service.consultarHistorico(fullPhone, num_pag);
	}
	
}
