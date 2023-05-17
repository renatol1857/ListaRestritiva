package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.dto.HistFreeDTO;
import com.renato.listrest.models.dto.FreeHistoricoTDO;
import com.renato.listrest.models.entities.HistFree;
import com.renato.listrest.models.entities.Free;
import com.renato.listrest.models.repositories.HistFreeRepository;
import com.renato.listrest.models.repositories.FreeRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class FreeService {
	@Autowired
	FreeRepository repo;
	
	@Autowired
	HistFreeRepository repoHist;
	
	@Autowired(required = true)
	private HttpServletRequest  request;

	public ResponseEntity<Free> save(String ddi, String ddd, String fone) {
		if (fone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "ddi/ddd/fone fora do padrao.");
		String remoteIP = request.getRemoteAddr();
		String fullFone = ddi + ddd + fone;
		Optional<Free> obj = repo.findByFullfone(fullFone);
		Free lstFree;
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
			lstFree = new Free(ddi, ddd, fone);
		lstFree = repo.save(lstFree);
		repoHist.save(new HistFree(lstFree, remoteIP, strDesc));
		return ResponseEntity.status(HttpStatus.CREATED).body(lstFree);
	}

	public ResponseEntity<Free> save(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<Free> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(obj.get());
		String remoteIP = request.getRemoteAddr();
		Free lstFree = new Free(fullfone);
		lstFree = repo.save(lstFree);
		repoHist.save(new HistFree(lstFree, remoteIP, "Created with fullPhone"));
		return ResponseEntity.status(HttpStatus.CREATED).body(lstFree);
	}
	
	
	public Free consultar(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, String.format("Fone [%s] fora do padrão", fullfone));
		Optional<Free> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		throw new CustomErrorException(HttpStatus.NOT_FOUND, String.format("Fone [%s] não encontrado", fullfone));
	}
	
	public Free consultarInc(String fullfone) {
		Free free = consultar(fullfone);
		String remoteIP = request.getRemoteAddr();
		repoHist.save(new HistFree(free, remoteIP, "Consult with fullPhone"));
		return free;
	}
	
	public FreeHistoricoTDO consultarHistorico(String fullfone, String numPag) {
		Free free = consultar(fullfone);
		FreeHistoricoTDO freeDTO = FreeHistoricoTDO.transfonaEmDTO(free);
		Iterable<HistFree> lstHist = repoHist.findAllByFree(free);
		
		for (HistFree histFree : lstHist) {
			freeDTO.getHistFreeDTO().add(HistFreeDTO.transfonaEmDTO(histFree));
		}
		return freeDTO;
	}

	public Iterable<Free> findAll(int numPag) {
		Pageable page = PageRequest.of(numPag, 20);
		return repo.findAll(page);
	}

	@Transactional
	public void apagar(String fullPhone) {
		Free free = consultar(fullPhone);
		// Iterable<HistFree> lstAx = repoHist.findAllByFree(free);
		repoHist.deleteAllByFree(free);
		repo.delete(free);
	}
	
}
