package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.repositories.DNISRepository;
import com.renato.listrest.util.LogSrv;

@Service
public class DNISService {
	
	@Autowired
	private DNISRepository dnisRepository;

	public DNISService() {
	}

	public DNIS save(DNIS dnis) {
		LogSrv.logger.info("DNIS save " + dnis);
		if (dnis == null)
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "dnis fora do padrão.");
		Optional<DNIS> obj = dnisRepository.findByDnisIgnoreCase(dnis.getDnis());
		if (obj.isPresent())
			throw new CustomErrorException(HttpStatus.CONFLICT, "dnis ja cadastrado.");
		return dnisRepository.save(dnis);
	}
	
	public DNIS findByID(Long id) {
		LogSrv.logger.info("DNIS findByID " + id);		
		
		Optional<DNIS> obj = dnisRepository.findById(id);
		return obj.orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND, "ID DNIS not found - "+id));
	}
	
	public DNIS findByDnis(String dnis) {
		LogSrv.logger.info("DNIS findByDnis " + dnis);		
		
		Optional<DNIS> obj = dnisRepository.findByDnisIgnoreCase(dnis);
		return obj.orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND, "DNIS not found - "+dnis));
	}
	
	
	
	
	
	
}
