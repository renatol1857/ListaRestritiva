package com.renato.listrest.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.dto.RestritivaDTO;
import com.renato.listrest.models.entities.Restritiva;
import com.renato.listrest.models.repositories.RestritivaRepository;

@Service
public class RestritivaService {
	@Autowired
	RestritivaRepository repo;

	public Restritiva save(String ddi, String ddd, String fone) {
		if (fone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "ddi/ddd/fone fora do padrao.");
		String fullFone = ddi + ddd + fone;
		Optional<Restritiva> obj = repo.findByFullfone(fullFone);
		// Optional<ListaRestGeral> obj = repo.findByDdiAndDddAndFone(ddi, ddd, fone);
		Restritiva ltRest;
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
		} else
			ltRest = new Restritiva(ddi, ddd, fone);
		return repo.save(ltRest);
	}

	public Restritiva save(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<Restritiva> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		Restritiva ltRest = new Restritiva(fullfone);
		return repo.save(ltRest);
	}

	public List<RestritivaDTO> findAll() {
		Iterable<Restritiva> lst = repo.findAll();
		List<RestritivaDTO> lstDTO = new ArrayList<>();
		for (Restritiva listGeral : lst) 
			lstDTO.add(RestritivaDTO.transfonaEmDTO(listGeral));
		//	Consumer<ListaRestGeralDTO> fnc = obj -> ListaRestGeralDTO.transfonaEmDTO(obj);
		//List<ListaRestGeralDTO> lstDTO = lst.forEach();
		return lstDTO;
	}

	public Restritiva consultarFullFone(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<Restritiva> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		throw new CustomErrorException(HttpStatus.NOT_FOUND, String.format("Fone [%s] não encontrado", fullfone));
	}

}
