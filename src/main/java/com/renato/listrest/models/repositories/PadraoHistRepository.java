package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.PadraoHist;

@Repository
public interface PadraoHistRepository extends CrudRepository<PadraoHist, Long>{

}
