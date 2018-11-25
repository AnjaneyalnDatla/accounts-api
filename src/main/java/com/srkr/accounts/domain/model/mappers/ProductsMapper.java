package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.Products;

@Component
public class ProductsMapper {

	public Products toPostgresObject(com.srkr.accounts.domain.model.Products products) {
		Products pgProducts = new Products();
		pgProducts.setId(products.getId());
		pgProducts.setName(products.getName());
		return pgProducts;
	}

	public com.srkr.accounts.domain.model.Products toDomainObject(Products pgProducts) {
		if (null != pgProducts)
			return new com.srkr.accounts.domain.model.Products(pgProducts.getId(), pgProducts.getName());
		return null;
	}

	public List<com.srkr.accounts.domain.model.Products> toListOfDomainObjects(List<Products> pgProducts) {
		List<com.srkr.accounts.domain.model.Products> products = new ArrayList<>();
		pgProducts.forEach(c -> {
			products.add(toDomainObject(c));
		});
		return products;
	}
}
