package com.a2nine.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.a2nine.accounts.domain.model.postgres.Products;

public interface PostgresProductsRepository extends Repository<Products, Long> {

	List<Products> findByOrgcode(String orgCode);
	
	Products save(Products products);

}
