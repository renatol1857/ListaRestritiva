package com.renato.listrest.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.ListaRest;

@Repository
public interface ListaRestRepository extends CrudRepository<ListaRest, Long> {
	
	public Optional<ListaRest> findByDdiAndDddAndFone(String ddi, String ddd, String fone);
	
	public Optional<ListaRest> findByFullfone(String fullfone);

}
