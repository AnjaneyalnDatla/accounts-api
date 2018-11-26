package com.srkr.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.srkr.accounts.domain.model.postgres.HeaderTypes;

public interface PostgresHeaderTypesRepository extends Repository<HeaderTypes, Long>{
	
	HeaderTypes save(HeaderTypes headerTypes);
	
	List<HeaderTypes> findAll();
	
	HeaderTypes findById(Long id);

}
