package com.srkr.accounts.domain.model.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.srkr.accounts.domain.model.postgres.Products;

public interface PostgresProductsRepository extends Repository<Products, Long> {

	List<Products> findAll();

}
