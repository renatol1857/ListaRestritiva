package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.Padrao;

@Repository
public interface PadraoRepository extends CrudRepository<Padrao, Long>{

}
