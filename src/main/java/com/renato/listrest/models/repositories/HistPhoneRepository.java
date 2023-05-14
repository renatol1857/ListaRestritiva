package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.HistFone;
import com.renato.listrest.models.entities.Phone;

@Repository
public interface HistPhoneRepository extends CrudRepository<HistFone, Long>{

	public void deleteByPhone(Phone phone);
}
