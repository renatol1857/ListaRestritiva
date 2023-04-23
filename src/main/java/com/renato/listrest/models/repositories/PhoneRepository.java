package com.renato.listrest.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.entities.Phone;

@Repository
public interface PhoneRepository extends CrudRepository<Phone, Long>{
	public Optional<Phone> findByDnisAndFullfone(DNIS dnis, String fullFone); 
}
