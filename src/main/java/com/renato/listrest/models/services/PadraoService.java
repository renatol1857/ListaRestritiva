package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.renato.listrest.errors.ResponseGeralRL;
import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.entities.Padrao;
import com.renato.listrest.models.entities.PadraoHist;
import com.renato.listrest.models.entities.Phone;
import com.renato.listrest.models.enums.PadroesEn;
import com.renato.listrest.models.repositories.PadraoHistRepository;
import com.renato.listrest.models.repositories.PadraoRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PadraoService {
	@Autowired
	PadraoRepository repo;
	
	@Autowired
	PadraoHistRepository repoHist;
	
	@Autowired(required = true)
	private HttpServletRequest request;
	
	public ResponseEntity<Padrao> save(Padrao padrao) {
		if (! PadroesEn.isValid(padrao.getTipoPadrao().getCod()) )
			throw new CustomErrorException(HttpStatus.BAD_REQUEST, "tipo de Padrao nao localizado");
		switch(padrao.getTipoPadrao()) {
			case START_WITH:
			case END_WITH:
			case CONTENT:
			case NOT_DDI:
			case NOT_DDD:
			case NOT_SEQUENTIAL:
			case LENGTH_MAX:
			case LENGTH_MIN:
				if (padrao.getExtra().length()<=0)
					throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Para este padrão é necesario valor Extra");
				break;
			default:
				// NOT_MOBILE; NOT_LANDLINE; NOT_ANONYMOUS; NOT_STRING:
				padrao.setExtra("");
				break;
			}
		Optional<Padrao> obj = repo.findByTipoPadraoAndExtra(padrao.getTipoPadrao(), padrao.getExtra());
		if (obj.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(obj.get());
		String remoteIP = request.getRemoteAddr();
		padrao = repo.save(padrao);
		repoHist.save(new PadraoHist(padrao, remoteIP, "Created Pattern"));
		return ResponseEntity.status(HttpStatus.CREATED).body(padrao);
	}
	
	public Iterable<Padrao> findAll(int numPage){
		return repo.findAll();
	}

	private boolean isNumerico(String fullPhone){
		return fullPhone.matches(String.format("\\\\d{%d}",fullPhone.length())); 
	}
	
	private Phone quebrarFullPhone(String fullPhone){
		Phone phone = new Phone();
		phone.setFullfone(fullPhone);
		phone.setDdi("");
		phone.setDdd("");;
		phone.setFone(fullPhone);
		switch(fullPhone.length()) {
			case 7: // Fixo 155 5610
				return phone;
			case 8: // fixo 2155 5610
				return phone;
			case 9: // Celular 92155 5610
				return phone;
			case 10: // ddd + fixo 11 2155 5610 
				phone.setDdd(fullPhone.substring(0,2));
				phone.setFone(fullPhone.substring(2));
				return phone;
			case 11: // ddd + Celular 11 92155 5610
				phone.setDdd(fullPhone.substring(0,2));
				phone.setFone(fullPhone.substring(2));
				return phone;
			case 12: // ddd1 + ddd + fixo 55 11 21555610
				phone.setDdi(fullPhone.substring(0,2));
				phone.setDdd(fullPhone.substring(2,4));
				phone.setFone(fullPhone.substring(4));
				return phone;
			case 13: // ddd1 + ddd + celular 55 11 921555610
				phone.setDdi(fullPhone.substring(0,2));
				phone.setDdd(fullPhone.substring(2,4));
				phone.setFone(fullPhone.substring(4));
				return phone;
			default:
				if (fullPhone.length() >= 13 ) {
					phone.setDdi(fullPhone.substring(0,2));
					phone.setDdd(fullPhone.substring(2,4));
					phone.setFone(fullPhone.substring(4));
				}
				break;
		}
		return phone;
	}
	
	@SuppressWarnings("incomplete-switch")
	public Optional<ResponseGeralRL> analisarFullPhone(String fullPhone){

		ResponseGeralRL resp =  new ResponseGeralRL(); //quebrarFullPhone (fullPhone);
		Iterable<Padrao> padroes = repo.findAll();
		for (Padrao padrao : padroes) {
			switch(padrao.getTipoPadrao()) {
				case NOT_STRING:
					if (!isNumerico(fullPhone)) {
						resp.setCodMsg(20);
						resp.setMessage("Phone doesn't support String.");
						resp.setRc(20);
						//resp.setHttpCode(HttpStatus.FORBIDDEN);
						
						return Optional.of(resp);
						}
					break;
				case NOT_ANONYMOUS:
					if ( fullPhone.equalsIgnoreCase("ANONYMOUS") )
						return Optional.of(resp);
					break;
			}
		}
		boolean flagSair = false;
		for (Padrao padrao : padroes) {
			switch(padrao.getTipoPadrao()) {
				case START_WITH:
					break;
				case END_WITH:
					break;
				case CONTENT:
					break;
				case NOT_DDI:
					break;
				case NOT_DDD:
					break;
				case NOT_SEQUENTIAL:
					break;
				case LENGTH_MAX:
					break;
				case LENGTH_MIN:
					break;
				case NOT_MOBILE:
					break;
				case NOT_LANDLINE:
					break;
			}
			if  (flagSair)
				return Optional.of(resp);
		}
		return Optional.empty();
	}
}
