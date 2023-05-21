package com.renato.listrest.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.Padrao;
import com.renato.listrest.models.enums.PadroesEn;

@Repository
public interface PadraoRepository extends CrudRepository<Padrao, Long>{
	
	Optional<Padrao> findByTipoPadraoAndExtra(PadroesEn tipoPadrao, String extra);

}
