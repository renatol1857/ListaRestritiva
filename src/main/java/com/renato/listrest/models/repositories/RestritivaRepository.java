package com.renato.listrest.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.Restritiva;

@Repository
public interface RestritivaRepository extends CrudRepository<Restritiva, Long> {
	
	public Optional<Restritiva> findByDdiAndDddAndFone(String ddi, String ddd, String fone);
	
	public Optional<Restritiva> findByFullfone(String fullfone);

}
