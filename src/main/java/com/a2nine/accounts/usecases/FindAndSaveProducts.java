package com.a2nine.accounts.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a2nine.accounts.domain.model.Products;
import com.a2nine.accounts.domain.model.mappers.ProductsMapper;
import com.a2nine.accounts.domain.model.repositories.PostgresProductsRepository;

@Service
public class FindAndSaveProducts {

	@Autowired
	private PostgresProductsRepository postgresProductsRepository;

	@Autowired
	private ProductsMapper productsMapper;

	public List<Products> findAllProducts(String orgCode) {
		return productsMapper.toListOfDomainObjects(postgresProductsRepository.findByOrgcode(orgCode));
	}

	public Products saveProduct(Products products) {
		return productsMapper
				.toDomainObject(postgresProductsRepository.save(productsMapper.toPostgresObject(products)));
	}
}
