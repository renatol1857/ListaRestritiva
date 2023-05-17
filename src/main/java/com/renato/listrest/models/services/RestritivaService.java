package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.dto.RestritivaHistDTO;
import com.renato.listrest.models.dto.RestritivaHistLstDTO;
import com.renato.listrest.models.entities.Restritiva;
import com.renato.listrest.models.entities.RestritivaHist;
import com.renato.listrest.models.repositories.HistRestritivoRepository;
import com.renato.listrest.models.repositories.RestritivaRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class RestritivaService {
	@Autowired
	RestritivaRepository repo;
	
	@Autowired
	HistRestritivoRepository repoHist;
	
	@Autowired(required = true)
	private HttpServletRequest request;

	public ResponseEntity<Restritiva> save(String ddi, String ddd, String fone) {
		if (fone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "ddi/ddd/fone fora do padrao.");
		String fullFone = ddi + ddd + fone;
		Optional<Restritiva> obj = repo.findByFullfone(fullFone);
		Restritiva restritiva;
		String strDesc = "Created with DDI/DDD/Phone";
		if (obj.isPresent()) {
			restritiva = obj.get();
			boolean flagAlterou = false;
			if (!restritiva.getDdi().equals(ddi)) {
				restritiva.setDdi(ddi);
				flagAlterou = true;
			}
			if (!restritiva.getDdd().equals(ddd)) {
				restritiva.setDdd(ddd);
				flagAlterou = true;
			}
			if (!restritiva.getFone().equals(fone)) {
				restritiva.setFone(fone);
				flagAlterou = true;
			}
			if (!flagAlterou)
				return ResponseEntity.status(HttpStatus.OK).body(restritiva);
			strDesc = "Updated with DDI/DDD/Phone";
		} else
			restritiva = new Restritiva(ddi, ddd, fone);
		String remoteIP = request.getRemoteAddr();
		restritiva = repo.save(restritiva);
		repoHist.save(new RestritivaHist(restritiva, remoteIP, strDesc));
		return ResponseEntity.status(HttpStatus.CREATED).body(restritiva);
	}

	public ResponseEntity<Restritiva> save(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<Restritiva> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(obj.get());
		String remoteIP = request.getRemoteAddr();
		Restritiva restritiva = new Restritiva(fullfone);
		restritiva = repo.save(restritiva);
		repoHist.save(new RestritivaHist(restritiva, remoteIP, "Created with fullPhone"));
		return ResponseEntity.status(HttpStatus.CREATED).body(restritiva);
	}
	
	public Restritiva consultar(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, String.format("Fone [%s] fora do padrão", fullfone));
		Optional<Restritiva> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		throw new CustomErrorException(HttpStatus.NOT_FOUND, String.format("Fone [%s] não encontrado", fullfone));
	}

	public Restritiva consultarInc(String fullfone) {
		Restritiva obj = consultar(fullfone);
		String remoteIP = request.getRemoteAddr();
		repoHist.save(new RestritivaHist(obj, remoteIP, "Consult with fullPhone"));
		return obj;
	}	
	
	
	public Iterable<Restritiva> findAll(int numPag) {
		Pageable page = PageRequest.of(numPag, 20);
		return repo.findAll(page);
	}

	@Transactional
	public void apagar(String fullPhone) {
		Restritiva obj = consultar(fullPhone);
		repoHist.deleteAllByRestritiva(obj);
		repo.delete(obj);
	}	
	
	public RestritivaHistDTO consultarHistorico(String fullfone, String numPag) {
		Restritiva obj = consultar(fullfone);
		RestritivaHistDTO objDTO = RestritivaHistDTO.transfonaEmDTO(obj);
		Iterable<RestritivaHist> lst = repoHist.findAllByRestritiva(obj);
		for (RestritivaHist hist : lst) 
			objDTO.getRestritivaHistListDTO().add(RestritivaHistLstDTO.transfonaEmDTO(hist));
		return objDTO;
	}

	/*
	public List<RestritivaDTO> findAll() {
		Iterable<Restritiva> lst = repo.findAll();
		List<RestritivaDTO> lstDTO = new ArrayList<>();
		for (Restritiva listGeral : lst) 
			lstDTO.add(RestritivaDTO.transfonaEmDTO(listGeral));
		//	Consumer<ListaRestGeralDTO> fnc = obj -> ListaRestGeralDTO.transfonaEmDTO(obj);
		//List<ListaRestGeralDTO> lstDTO = lst.forEach();
		return lstDTO;
	}
	*/

	public Restritiva consultarFullFone(String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrao.");
		Optional<Restritiva> obj = repo.findByFullfone(fullfone);
		if (obj.isPresent())
			return obj.get();
		throw new CustomErrorException(HttpStatus.NOT_FOUND, String.format("Fone [%s] não encontrado", fullfone));
	}

}
