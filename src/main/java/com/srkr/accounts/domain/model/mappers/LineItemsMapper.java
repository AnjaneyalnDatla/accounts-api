package com.srkr.accounts.domain.model.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.LineItem;

@Component
public class LineItemsMapper {

	@Autowired
	private ProductsMapper productsMapper;

	public LineItem toPostgresObject(com.srkr.accounts.domain.model.LineItem lineItem) {
		LineItem pgLineItem = new LineItem();
		pgLineItem.setId(lineItem.id());
		pgLineItem.setTransaction_number(lineItem.transaction_number());
		pgLineItem.setLine_item_number(lineItem.line_item_number());
		pgLineItem.setProducts(productsMapper.toPostgresObject(lineItem.products()));
		pgLineItem.setName(lineItem.name());
		pgLineItem.setQuantity(lineItem.quantity());
		pgLineItem.setPrice(lineItem.price());
		pgLineItem.setAmount(lineItem.amount());
		pgLineItem.setDateupdated(lineItem.dateupdated());
		return pgLineItem;
	}

	public com.srkr.accounts.domain.model.LineItem toDomainObject(LineItem pgLineItem) {
		return new com.srkr.accounts.domain.model.LineItem(pgLineItem.getId(), pgLineItem.getTransaction_number(),
				pgLineItem.getLine_item_number(), productsMapper.toDomainObject(pgLineItem.getProducts()),
				pgLineItem.getName(), pgLineItem.getQuantity(), pgLineItem.getPrice(), pgLineItem.getAmount(),
				pgLineItem.getDateupdated());

	}

}
