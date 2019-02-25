package com.a2nine.accounts.domain.model.repositories;

import java.util.Set;

import org.springframework.data.repository.Repository;

import com.a2nine.accounts.domain.model.postgres.LineItem;

public interface PostgresLineItemsRepository extends Repository<LineItem, Long> {

	Set<LineItem> findByTransactionNumber(Integer transaction_number);

	LineItem save(LineItem lineItem);
	
	LineItem delete(LineItem lineItem);

}
