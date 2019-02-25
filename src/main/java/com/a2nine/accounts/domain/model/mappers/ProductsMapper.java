package com.a2nine.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.a2nine.accounts.domain.model.Organisation;
import com.a2nine.accounts.domain.model.postgres.Products;

@Component
public class ProductsMapper {

	public Products toPostgresObject(com.a2nine.accounts.domain.model.Products products) {
		Products pgProducts = new Products();
		pgProducts.setId(products.getId());
		pgProducts.setName(products.getName());
		pgProducts.setOrgcode(products.organisation().code());
		pgProducts.setOrgName(products.organisation().name());
		return pgProducts;
	}

	public com.a2nine.accounts.domain.model.Products toDomainObject(Products pgProducts) {
		if (null != pgProducts)
			return new com.a2nine.accounts.domain.model.Products(pgProducts.getId(), pgProducts.getName(),
					new Organisation(pgProducts.getOrgName(), pgProducts.getOrgcode()));
		return null;
	}

	public List<com.a2nine.accounts.domain.model.Products> toListOfDomainObjects(List<Products> pgProducts) {
		List<com.a2nine.accounts.domain.model.Products> products = new ArrayList<>();
		pgProducts.forEach(c -> {
			products.add(toDomainObject(c));
		});
		return products;
	}
}
