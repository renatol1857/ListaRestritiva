package com.renato.listrest.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.Free;

@Repository
public interface FreeRepository extends PagingAndSortingRepository<Free, Long>, CrudRepository<Free, Long>{
	
	public Optional<Free> findByDdiAndDddAndFone(String ddi, String ddd, String fone);
	
	public Optional<Free> findByFullfone(String fullfone);
	
	

}
