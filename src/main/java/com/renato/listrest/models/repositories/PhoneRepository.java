package com.renato.listrest.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.entities.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{
	public Optional<Phone> findByDnisAndFullfone(DNIS dnis, String fullFone); 
	public Optional<Phone> findByFullfone(String fullFone); 
	
	public Optional<Phone> findByDnis(DNIS dnis); 
	
	public void  deleteByDnis(DNIS dnis); 
	
	
}
