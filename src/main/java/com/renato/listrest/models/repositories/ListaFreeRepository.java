package com.renato.listrest.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.ListaFree;

@Repository
public interface ListaFreeRepository extends CrudRepository<ListaFree, Long>{
	
	public Optional<ListaFree> findByDdiAndDddAndFone(String ddi, String ddd, String fone);
	
	public Optional<ListaFree> findByFullfone(String fullfone);

}
