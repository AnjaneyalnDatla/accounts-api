package com.a2nine.accounts.domain.model.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.a2nine.accounts.domain.model.postgres.LineItem;

@Component
public class LineItemsMapper {

	@Autowired
	private ProductsMapper productsMapper;

	public LineItem toPostgresObject(com.a2nine.accounts.domain.model.LineItem lineItem) {
		LineItem pgLineItem = new LineItem();
		pgLineItem.setId(lineItem.id());
		pgLineItem.setTransactionNumber(lineItem.transaction_number());
		pgLineItem.setLine_item_number(lineItem.line_item_number());
		pgLineItem
				.setProducts(null != lineItem.products() ? productsMapper.toPostgresObject(lineItem.products()) : null);
		pgLineItem.setName(lineItem.name());
		pgLineItem.setQuantity(lineItem.quantity());
		pgLineItem.setPrice(lineItem.price());
		pgLineItem.setAmount(lineItem.amount());
		pgLineItem.setDateupdated(lineItem.dateupdated());
		return pgLineItem;
	}

	public com.a2nine.accounts.domain.model.LineItem toDomainObject(LineItem pgLineItem) {
		return new com.a2nine.accounts.domain.model.LineItem(pgLineItem.getId(), pgLineItem.getTransactionNumber(),
				pgLineItem.getLine_item_number(), productsMapper.toDomainObject(pgLineItem.getProducts()),
				pgLineItem.getName(), pgLineItem.getQuantity(), pgLineItem.getPrice(), pgLineItem.getAmount(),
				pgLineItem.getDateupdated());

	}

	public Set<com.a2nine.accounts.domain.model.LineItem> toListDomainObject(Set<LineItem> pgList) {
		if (pgList == null)
			return null;

		return pgList.stream().map((pgLt) -> toDomainObject(pgLt)).collect(Collectors.toSet());
	}

	public Set<LineItem> toListPostgresObject(Set<com.a2nine.accounts.domain.model.LineItem> aList) {
		return aList.stream().map((lt) -> toPostgresObject(lt)).collect(Collectors.toSet());
	}
}
