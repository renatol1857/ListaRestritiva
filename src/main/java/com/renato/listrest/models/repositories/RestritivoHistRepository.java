package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;

import com.renato.listrest.models.entities.RestritivaHist;
import com.renato.listrest.models.entities.Restritiva;

public interface RestritivoHistRepository extends CrudRepository<RestritivaHist, Long> {
	
	public Iterable<RestritivaHist> findAllByRestritiva(Restritiva restritiva);
	
	public void deleteAllByRestritiva (Restritiva restritiva);

}
