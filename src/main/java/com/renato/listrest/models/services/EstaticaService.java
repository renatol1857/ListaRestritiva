package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.renato.listrest.errors.ResponseGeralRL;

@Service
public class EstaticaService {
	
	@Autowired
	PadraoService servicePadrao;
	
	@Autowired
	FreeService serviceFree;
	
	@Autowired
	RestritivaService serviceRestritiva;
	
	public ResponseEntity<ResponseGeralRL> consultar(String fullPhone){
		// boolean flagSair=false;
		ResponseGeralRL resp = new ResponseGeralRL(); 
		
		if ( fullPhone.length() > 4) {
			Optional<ResponseGeralRL> obj = servicePadrao.analisarFullPhone(fullPhone);
			if (obj.isPresent()) 
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(obj.get());
		}
			
		return null;
	}
	
	
}
