package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.entities.HistFree;
import com.renato.listrest.models.entities.ListaFree;
import com.renato.listrest.models.repositories.HistFreeRepository;
import com.renato.listrest.models.repositories.ListaFreeRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ListaFreeService {
	@Autowired
	ListaFreeRepository repo;
	
	@Autowired
	HistFreeRepository repoHist;
	
	@Autowired(required = true)
	private HttpServletRequest  request;

	public ResponseEntity<ListaFree> save(String ddi, String ddd, String fone) {
		if (fone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "ddi/ddd/fone fora do padrao.");
		String remoteIP = request.getRemoteAddr();
		String fullFone = ddi + ddd + fone;
		Optional<ListaFree> obj = repo.findByFullfone(fullFone);
		ListaFree lstFree;
		String strDesc = "Created with DDI/DDD/Phone";
		if (obj.isPresent()) {
			lstFree = obj.get();
			boolean flagAlterou = false;
			if (!lstFree.getDdi().equals(ddi)) {
				lstFree.setDdi(ddi);
				flagAlterou = true;
			}
			if (!lstFree.getDdd().equals(ddd)) {
				lstFree.setDdd(ddd);
				flagAlterou = true;
			}
			if (!lstFree.getFone().equals(fone)) {
				lstFree.setFone(fone);
				flagAlterou = true;
			}
			if (!flagAlterou)
				return ResponseEntity.status(HttpStatus.OK).body(lstFree);
			strDesc = "Updated with DDI/DDD/Phone";
		} else 
			lstFree = new ListaFree(ddi, ddd, fone);
		lstFree = repo.save(lstFree);
		repoHist.save(new HistFree(lstFree, remoteIP, strDesc));
		return ResponseEntity.status(HttpStatus.CREATED).body(lstFree);
	}

	public ResponseEntity<ListaFree> save(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<ListaFree> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(obj.get());
		String remoteIP = request.getRemoteAddr();
		ListaFree lstFree = new ListaFree(fullfone);
		lstFree = repo.save(lstFree);
		repoHist.save(new HistFree(lstFree, remoteIP, "Created with fullPhone"));
		return ResponseEntity.status(HttpStatus.CREATED).body(lstFree);
	}
	
	
	public ListaFree consultar(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, String.format("Fone [%s] fora do padrão", fullfone));
		Optional<ListaFree> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		throw new CustomErrorException(HttpStatus.NOT_FOUND, String.format("Fone [%s] não encontrado", fullfone));
	}
	
	public ListaFree consultarInc(String fullfone) {
		ListaFree free = consultar(fullfone);
		String remoteIP = request.getRemoteAddr();
		repoHist.save(new HistFree(free, remoteIP, "Consult with fullPhone"));
		return free;
	}
	
	public ListaFree consultarHistorico(String fullfone, String numPag) {
		ListaFree free = consultar(fullfone);
		return free;
	}

	
	public Iterable<ListaFree> findAll() {
		return repo.findAll();
	}


}
