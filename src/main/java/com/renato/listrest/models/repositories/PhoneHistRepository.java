package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.PhoneHist;
import com.renato.listrest.models.entities.Phone;

@Repository
public interface PhoneHistRepository extends CrudRepository<PhoneHist, Long>{

	public void deleteByPhone(Phone phone);
}
