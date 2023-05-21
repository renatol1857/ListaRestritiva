package com.renato.listrest.models.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.renato.listrest.exceptions.CustomErrorException;
import com.renato.listrest.models.entities.Padrao;
import com.renato.listrest.models.entities.PadraoHist;
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
	
	
}
