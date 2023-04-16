package com.renato.listrest.models.services;

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
	
	
	
	
	
}
