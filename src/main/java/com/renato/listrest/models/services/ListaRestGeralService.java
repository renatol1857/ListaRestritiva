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
	ListaRestGeralRepository repo;
	
	public ListaRestGeral save(String ddi, String ddd, String fone) {
		if (fone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "ddi/ddd/fone fora do padrao.");
		String fullFone = ddi+ddd+fone;
		Optional<ListaRestGeral> obj = repo.findByFullfone(fullFone);
		// Optional<ListaRestGeral> obj = repo.findByDdiAndDddAndFone(ddi, ddd, fone);
		ListaRestGeral ltRest;
		if (obj.isPresent()) {
			ltRest = obj.get();
			boolean flagAlterou = false;
			if (!ltRest.getDdi().equals(ddi)) {
				ltRest.setDdi(ddi);
				flagAlterou = true;
			}
			if (!ltRest.getDdd().equals(ddd)) {
				ltRest.setDdd(ddd);			
				flagAlterou = true;
			}
			if (!ltRest.getFone().equals(fone)) {
				ltRest.setFone(fone);	
				flagAlterou = true;
			}
			if (!flagAlterou)
				return ltRest;
		}
		else
			ltRest = new ListaRestGeral(ddi, ddd, fone);
		return repo.save(ltRest);
	}

	public ListaRestGeral save(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<ListaRestGeral> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		ListaRestGeral ltRest = new ListaRestGeral(fullfone);
		return repo.save(ltRest);
	}

}
