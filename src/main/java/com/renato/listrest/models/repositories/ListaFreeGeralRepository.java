package com.renato.listrest.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.ListaFreeGeral;

@Repository
public interface ListaFreeGeralRepository extends CrudRepository<ListaFreeGeral, Long>{
	
	public Optional<ListaFreeGeral> findByDdiAndDddAndFone(String ddi, String ddd, String fone);
	
	public Optional<ListaFreeGeral> findByFullfone(String fullfone);

}
