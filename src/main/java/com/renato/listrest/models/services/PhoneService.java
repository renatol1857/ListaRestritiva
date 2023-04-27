package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.dto.PhoneSalvarRespostaDTO;
import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.entities.Phone;
import com.renato.listrest.models.enums.StatusEn;
import com.renato.listrest.models.repositories.PhoneRepository;

@Service
public class PhoneService {
	@Autowired
	private PhoneRepository phoneRepository;
	@Autowired
	private DNISService dnisService;
	@Autowired
	private HistPhoneService histPhoneService;

	public PhoneSalvarRespostaDTO save(String sDnis, String fullfone) {
		if (fullfone.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "fullfone fora do padrão.");
		if (sDnis.isEmpty())
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "dnis fora do padrão.");
		DNIS dnis = dnisService.findByDnis(sDnis);
		 
		/*#FIXME ajutar o StatusEn, nao esta gravando corretamente os valores na tbDNIS (status) */
		if (dnis.getStatus() != StatusEn.ATIVO) 
		 	throw new CustomErrorException(HttpStatus.BAD_REQUEST, "DNIS não está ativo.");
		Phone fone = null;
		Optional<Phone> obj = phoneRepository.findByDnisAndFullfone(dnis, fullfone);
		if (obj.isPresent())
			fone = obj.get();
		else {
			fone = new Phone(dnis, fullfone);
			fone = phoneRepository.save(fone);
		}
		histPhoneService.save(fone);
		return PhoneSalvarRespostaDTO.transfonaEmDTO(fone);
	}

	public void consultarFone(String mcdu, String fullFone ) {
		
	}
	
	public Phone save(String mcdu, String ddi, String ddd, String Phone) {

		return null;
	}

}
