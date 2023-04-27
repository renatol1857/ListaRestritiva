package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private DNISRepository dnisRepository;

	public DNISService() {
	}

	public DNIS save(DNIS dnis) {
		LogSrv.logger.info("DNIS save " + dnis);
		Optional<DNIS> obj = dnisRepository.findByDnisIgnoreCase(dnis.getDnis());
		if (obj.isPresent())
			throw new CustomErrorException(HttpStatus.CONFLICT, "dnis ja cadastrado.");
		return dnisRepository.save(dnis);
	}

	@Transactional
	public DNIS update(DNIS dnis) {
		// LogSrv.logger.info("update save " + dnis);
		Optional<DNIS> obj = dnisRepository.findById(dnis.getId());
		if (!obj.isPresent())
			throw new CustomErrorException(HttpStatus.CONFLICT, "ID nao cadastrado.");
		DNIS regDnis = obj.get();
		if (!regDnis.getDnis().equals(dnis.getDnis()))
			throw new CustomErrorException(HttpStatus.CONFLICT, "dnis nao pode ser alterado.");
		regDnis.setAlias(dnis.getAlias());
		regDnis.setDescricao(dnis.getDescricao());
		regDnis.setStatus(dnis.getStatus());
		// dnisRepository.upDnisById(dnis.getAlias(),dnis.getDescricao(),
		// dnis.getStatus(),dnis.getId());

		return dnisRepository.save(regDnis);
	}

	public DNIS findByID(Long id) {
		LogSrv.logger.info("DNIS findByID " + id);

		Optional<DNIS> obj = dnisRepository.findById(id);
		return obj.orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND, "DNIS not found - id=" + id));
	}

	public DNIS findByDnis(String mcdu) {
		LogSrv.logger.info("DNIS findByDnis " + mcdu);

		Optional<DNIS> obj = dnisRepository.findByDnisIgnoreCase(mcdu);
		return obj.orElseThrow(() -> new CustomErrorException(HttpStatus.NOT_FOUND, "MCDU not found - " + mcdu));
	}

	public DNIS consultarFone(String mcdu, String fullFone) {
		LogSrv.logger.info("DNIS findByDnis " + mcdu);
		DNIS dnis = findByDnis(mcdu);
		if (dnis.getStatus() != StatusEn.ATIVO)
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "DNIS não está ativo.");

		return dnis;
	}

}
