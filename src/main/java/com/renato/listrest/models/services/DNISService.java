package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.enums.StatusEn;
import com.renato.listrest.models.repositories.DNISRepository;
import com.renato.listrest.util.LogSrv;

import jakarta.transaction.Transactional;

@Service
public class DNISService {

	@Autowired
	private DNISRepository repo;
	

	public DNIS save(DNIS dnis) {
		LogSrv.logger.info("DNIS save " + dnis);
		Optional<DNIS> obj = repo.findByDnisIgnoreCase(dnis.getDnis());
		if (obj.isPresent())
			return obj.get();
		return repo.save(dnis);
	}

	@Transactional
	public DNIS update(DNIS dnis) {
		// LogSrv.logger.info("update save " + dnis);
		Optional<DNIS> obj = repo.findById(dnis.getId());
		if (!obj.isPresent())
			return save(dnis);
		DNIS regDnis = obj.get();
		if (!regDnis.getDnis().equals(dnis.getDnis()))
			throw new CustomErrorException(HttpStatus.CONFLICT, "DNIS nao pode ser alterado.");
		regDnis.setAlias(dnis.getAlias());
		regDnis.setDescricao(dnis.getDescricao());
		regDnis.setStatus(dnis.getStatus());
		// dnisRepository.upDnisById(dnis.getAlias(),dnis.getDescricao(),dnis.getStatus(),dnis.getId());
		return repo.save(regDnis);
	}

	public DNIS findByID(Long id) {
		LogSrv.logger.info("DNIS findByID " + id);
		Optional<DNIS> obj = repo.findById(id);
		return obj.orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND, "DNIS not found - id=" + id));
	}

	public ResponseEntity<DNIS> deleteCascade(String mcdu) {
		Optional<DNIS> obj = repo.findByDnisIgnoreCase(mcdu);
		if (!obj.isPresent())
			throw new CustomErrorException(HttpStatus.NOT_FOUND, String.format("MCDU [%s]not found ", mcdu));
		//phoneService.apagarByDNIS(obj.get());
		repo.deleteCascadeByID(obj.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body(obj.get());
	}

	public DNIS findByDnis(String mcdu) {
		LogSrv.logger.info("DNIS findByDnis " + mcdu);
		Optional<DNIS> obj = repo.findByDnisIgnoreCase(mcdu);
		return obj.orElseThrow(
				() -> new CustomErrorException(HttpStatus.NOT_FOUND, String.format("MCDU [%s]not found ", mcdu)));
	}

	public Iterable<DNIS> findAll() {
		return repo.findAll();
	}

	public Iterable<DNIS> findAll(int numPag) {
		Pageable page = PageRequest.of(numPag, 5);
		return repo.findAll(page);
	}

	public DNIS consultarFone(String mcdu, String fullFone) {
		LogSrv.logger.info("DNIS findByDnis " + mcdu);
		DNIS dnis = findByDnis(mcdu);
		if (dnis.getStatus() != StatusEn.ATIVO)
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, String.format("MCDU [%s] não está ativo.", mcdu));
		return dnis;
	}

}
