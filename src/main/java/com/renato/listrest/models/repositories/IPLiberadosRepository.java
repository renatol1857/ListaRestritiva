package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.IPLiberados;

@Repository
public interface IPLiberadosRepository extends CrudRepository<IPLiberados, Long> {

}
