package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.DNIS;

@Repository
public interface DNISRepository extends CrudRepository<DNIS, Long>{	


}
