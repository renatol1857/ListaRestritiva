package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.HistFone;

@Repository
public interface HistPhoneRepository extends CrudRepository<HistFone, Long>{

}
