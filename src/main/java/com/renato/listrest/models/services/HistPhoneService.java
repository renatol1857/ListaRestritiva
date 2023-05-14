package com.renato.listrest.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.listrest.models.entities.HistFone;
import com.renato.listrest.models.entities.Phone;
import com.renato.listrest.models.repositories.HistPhoneRepository;

@Service
public class HistPhoneService {
	@Autowired
	private HistPhoneRepository repo;
	
	public HistFone save(Phone phone) {
		return repo.save(new HistFone(phone));
	}
	
	public void apagar(Phone fone) {
		repo.deleteByPhone(fone);
	}
}
