package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.Transactions;

@Component
public class TransactionsMapper {

	@Autowired
	private AccountsMapper accountsMapper;

	@Autowired
	private HeadersMapper headersMapper;

	@Autowired
	private ProductsMapper productsMapper;

	public Transactions toPostgresObject(com.srkr.accounts.domain.model.Transactions transactions) {
		Transactions pgTransactions = new Transactions();
		pgTransactions.setUserId(transactions.user_id());
		pgTransactions.setUserName(transactions.user_name());
		pgTransactions.setTransaction_number(transactions.transaction_number());
		pgTransactions.setLine_item_number(transactions.line_item_number());
		pgTransactions.setName(transactions.name());
		pgTransactions.setAccounts(accountsMapper.toPostgresObject(transactions.accounts()));
		pgTransactions.setHeaders(headersMapper.toPostgresObject(transactions.header()));
		pgTransactions.setProducts(productsMapper.toPostgresObject(transactions.products()));
		return pgTransactions;
	}

	public com.srkr.accounts.domain.model.Transactions toDomainObject(Transactions pgTransactions) {
		com.srkr.accounts.domain.model.Transactions transactions = new com.srkr.accounts.domain.model.Transactions(
				pgTransactions.getId(), pgTransactions.getUserId(), pgTransactions.getTransaction_number(),
				pgTransactions.getUserName(), pgTransactions.getLine_item_number(),
				productsMapper.toDomainObject(pgTransactions.getProducts()), pgTransactions.getName(),
				accountsMapper.toDomainObject(pgTransactions.getAccounts()), pgTransactions.getQuantity(),
				pgTransactions.getPrice(), pgTransactions.getAmount(),
				headersMapper.toDomainObject(pgTransactions.getHeaders()));
		return transactions;
	}

	public List<com.srkr.accounts.domain.model.Transactions> toListOfDomainObjects(List<Transactions> pgTransactions) {
		List<com.srkr.accounts.domain.model.Transactions> transactions = new ArrayList<>();
		pgTransactions.forEach(c -> {
			transactions.add(toDomainObject(c));
		});
		return transactions;
	}

}
