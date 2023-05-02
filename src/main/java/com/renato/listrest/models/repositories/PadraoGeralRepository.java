package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.PadraoGeral;

@Repository
public interface PadraoGeralRepository extends CrudRepository<PadraoGeral, Long>{

}
