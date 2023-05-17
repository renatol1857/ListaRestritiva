package com.renato.listrest.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.listrest.models.entities.PhoneHist;
import com.renato.listrest.models.entities.Phone;
import com.renato.listrest.models.repositories.PhoneHistRepository;

@Service
public class PhoneHistService {
	@Autowired
	private PhoneHistRepository repo;
	
	public PhoneHist save(Phone phone) {
		return repo.save(new PhoneHist(phone));
	}
	
	public void apagar(Phone fone) {
		repo.deleteByPhone(fone);
	}
}
