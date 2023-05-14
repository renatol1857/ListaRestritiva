package com.renato.listrest.models.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.renato.listrest.models.entities.DNIS;
import com.renato.listrest.models.enums.StatusEn;

@Repository
public interface DNISRepository extends PagingAndSortingRepository<DNIS, Long>, CrudRepository<DNIS, Long>{	

	public Optional<DNIS> findByDnisIgnoreCase(String dnis);  
	
	@Modifying
	@Query("DELETE FROM DNIS u WHERE u.id = :id")
	public void deleteCascadeByID(@Param("id") Long id);
		
	@Modifying
//	@Query("update DNIS u set u.alias = ?1, u.descricao = ?2, u.status = ?3 where u.id = ?4")
	@Query("update DNIS u set u.alias = :alias, u.descricao = :descricao, u.status = :status where u.id = :id")
	public void upDnisById(@Param("alias") String alias, @Param("descricao") String descricao, @Param("status") StatusEn status, @Param("id") Long id);
}
