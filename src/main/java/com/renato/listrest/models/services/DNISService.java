package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return dnisRepository.save(dnis);
	}
	
	public DNIS findByID(Long id) {
		LogSrv.logger.info("DNIS findByID " + id);		
		
		Optional<DNIS> obj = dnisRepository.findById(id);
		return obj.orElse(null);
	}
	
	
	
	
	
	
}
