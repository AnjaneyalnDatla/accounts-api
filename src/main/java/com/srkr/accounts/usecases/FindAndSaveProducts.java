package com.srkr.accounts.usecases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srkr.accounts.domain.model.Products;
import com.srkr.accounts.domain.model.mappers.ProductsMapper;
import com.srkr.accounts.domain.model.repositories.PostgresProductsRepository;

@Service
public class FindAndSaveProducts {

	@Autowired
	private PostgresProductsRepository postgresProductsRepository;

	@Autowired
	private ProductsMapper productsMapper;

	public List<Products> findAllProducts() {
		return productsMapper.toListOfDomainObjects(postgresProductsRepository.findAll());
	}

	public Products saveProduct(Products products) {
		return productsMapper
				.toDomainObject(postgresProductsRepository.save(productsMapper.toPostgresObject(products)));
	}
}
