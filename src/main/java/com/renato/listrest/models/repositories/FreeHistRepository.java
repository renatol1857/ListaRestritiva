package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.FreeHist;
import com.renato.listrest.models.entities.Free;

@Repository
public interface FreeHistRepository extends PagingAndSortingRepository<FreeHist, Long>, CrudRepository<FreeHist, Long> {
	
	public Iterable<FreeHist> findAllByFree(Free free);
	
	public void deleteAllByFree (Free free);
	
}
