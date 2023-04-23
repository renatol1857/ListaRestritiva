package com.renato.listrest.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.listrest.models.entities.HistFone;
import com.renato.listrest.models.entities.Phone;
import com.renato.listrest.models.repositories.HistPhoneRepository;

@Service
public class HistPhoneService {
	@Autowired
	private HistPhoneRepository histPhoneRepository;
	
	public HistFone save(Phone phone) {
		return histPhoneRepository.save(new HistFone(phone));
	}
}
