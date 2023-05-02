package com.renato.listrest.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.ListaRestGeral;

@Repository
public interface ListaRestGeralRepository extends CrudRepository<ListaRestGeral, Long> {
	
	public Optional<ListaRestGeral> findByDdiAndDddAndFone(String ddi, String ddd, String fone);
	
	public Optional<ListaRestGeral> findByFullfone(String fullfone);

}
