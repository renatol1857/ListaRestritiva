package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.entities.ListaFreeGeral;
import com.renato.listrest.models.repositories.ListaFreeGeralRepository;

@Service
public class ListaFreeGeralService {
	@Autowired
	ListaFreeGeralRepository repo;

	public ListaFreeGeral save(String ddi, String ddd, String fone) {
		if (fone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "ddi/ddd/fone fora do padrao.");
		String fullFone = ddi + ddd + fone;
		Optional<ListaFreeGeral> obj = repo.findByFullfone(fullFone);
		ListaFreeGeral ltRest;
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
			ltRest = new ListaFreeGeral(ddi, ddd, fone);
		return repo.save(ltRest);
	}

	public ListaFreeGeral save(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<ListaFreeGeral> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		ListaFreeGeral ltRest = new ListaFreeGeral(fullfone);
		return repo.save(ltRest);
	}

	public Iterable<ListaFreeGeral> findAll() {
		return repo.findAll();
	}

	public ListaFreeGeral consultarFullFone(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, String.format("Fone [%s] fora do padrão", fullfone));
		Optional<ListaFreeGeral> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		throw new CustomErrorException(HttpStatus.NOT_FOUND, String.format("Fone [%s] não encontrado", fullfone));
	}

}
