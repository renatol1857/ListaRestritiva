package com.renato.listrest.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.renato.listrest.models.entities.HistFree;
import com.renato.listrest.models.entities.Free;

public interface HistFreeRepository extends PagingAndSortingRepository<HistFree, Long>, CrudRepository<HistFree, Long> {
	
	public Iterable<HistFree> findAllByFree(Free free);
	
	public void deleteAllByFree (Free free);
	
}
