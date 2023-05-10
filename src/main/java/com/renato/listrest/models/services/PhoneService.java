package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.dto.PhoneRespostaDTO;
import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.entities.Phone;
import com.renato.listrest.models.enums.StatusEn;
import com.renato.listrest.models.repositories.PhoneRepository;
import com.renato.listrest.util.LogSrv;

@Service
public class PhoneService {
	@Autowired
	private PhoneRepository repo;
	@Autowired
	private DNISService dnisService;
	@Autowired
	private HistPhoneService histPhoneService;

	public PhoneRespostaDTO save(String sDnis, String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrão.");
		if (sDnis.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "dnis fora do padrão.");
		DNIS dnis = dnisService.findByDnis(sDnis);
		 
		if (dnis.getStatus() != StatusEn.ATIVO) 
		 	throw new CustomErrorException(HttpStatus.BAD_REQUEST, "DNIS não está ativo.");
		Phone fone = null;
		Optional<Phone> obj = repo.findByDnisAndFullfone(dnis, fullfone);
		if (obj.isPresent())
			fone = obj.get();
		else {
			fone = new Phone(dnis, fullfone);
			fone = repo.save(fone);
		}
		histPhoneService.save(fone);
		return PhoneRespostaDTO.transfonaEmDTO(fone);
	}

	public PhoneRespostaDTO consultarFone(String mcdu, String fullfone ) {
		LogSrv.logger.info("DNIS findByDnis " + mcdu);
		DNIS dnis = dnisService.findByDnis(mcdu);
		Optional<Phone> obj = repo.findByDnisAndFullfone(dnis, fullfone);
		if (obj.isPresent()) 
			return PhoneRespostaDTO.transfonaEmDTO(obj.get());
		throw new CustomErrorException(HttpStatus.BAD_REQUEST, String.format("[%s] não encontrado",fullfone));

	}
	
	public PhoneRespostaDTO save(String mcdu, String ddi, String ddd, String fone) {
		if (fone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "ddi/ddd/fone fora do padrao.");
		DNIS dnis = dnisService.findByDnis(mcdu);
		String fullFone = ddi+ddd+fone;
		Optional<Phone> obj = repo.findByDnisAndFullfone(dnis, fullFone);
		Phone ltRest;
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
				return PhoneRespostaDTO.transfonaEmDTO(ltRest);
		}
		else
			ltRest = new Phone(dnis, ddi, ddd, fone);
		ltRest = repo.save(ltRest);
		return PhoneRespostaDTO.transfonaEmDTO(ltRest);

	}

}
