package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.entities.ListaRestGeral;
import com.renato.listrest.models.repositories.ListaRestGeralRepository;

@Service
public class ListaRestGeralService {
	@Autowired
	ListaRestGeralRepository restRepository;
	
	public ListaRestGeral save(String ddi, String ddd, String fone) {
		if (fone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "ddi/ddd/fone fora do padrao.");
		Optional<ListaRestGeral> obj = restRepository.findByDdiAndDddAndFone(ddi, ddd, fone);
		if (obj.isPresent())
			return obj.get();
		ListaRestGeral ltRest = new ListaRestGeral(ddi, ddd, fone);
		return restRepository.save(ltRest);
	}

	public ListaRestGeral save(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<ListaRestGeral> obj = restRepository.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		ListaRestGeral ltRest = new ListaRestGeral(fullfone);
		return restRepository.save(ltRest);
		
	}

}
