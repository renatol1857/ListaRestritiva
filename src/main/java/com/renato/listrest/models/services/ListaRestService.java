package com.renato.listrest.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.dto.ListaRestDTO;
import com.renato.listrest.models.entities.ListaRest;
import com.renato.listrest.models.repositories.ListaRestRepository;

@Service
public class ListaRestService {
	@Autowired
	ListaRestRepository repo;

	public ListaRest save(String ddi, String ddd, String fone) {
		if (fone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "ddi/ddd/fone fora do padrao.");
		String fullFone = ddi + ddd + fone;
		Optional<ListaRest> obj = repo.findByFullfone(fullFone);
		// Optional<ListaRestGeral> obj = repo.findByDdiAndDddAndFone(ddi, ddd, fone);
		ListaRest ltRest;
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
			ltRest = new ListaRest(ddi, ddd, fone);
		return repo.save(ltRest);
	}

	public ListaRest save(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<ListaRest> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		ListaRest ltRest = new ListaRest(fullfone);
		return repo.save(ltRest);
	}

	public List<ListaRestDTO> findAll() {
		Iterable<ListaRest> lst = repo.findAll();
		List<ListaRestDTO> lstDTO = new ArrayList<>();
		for (ListaRest listGeral : lst) 
			lstDTO.add(ListaRestDTO.transfonaEmDTO(listGeral));
		//	Consumer<ListaRestGeralDTO> fnc = obj -> ListaRestGeralDTO.transfonaEmDTO(obj);
		//List<ListaRestGeralDTO> lstDTO = lst.forEach();
		return lstDTO;
	}

	public ListaRest consultarFullFone(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<ListaRest> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		throw new CustomErrorException(HttpStatus.NOT_FOUND, String.format("Fone [%s] não encontrado", fullfone));
	}

}
