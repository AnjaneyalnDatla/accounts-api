package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.srkr.accounts.domain.model.postgres.Accounts;
import com.srkr.accounts.domain.model.postgres.Headers;
import com.srkr.accounts.domain.model.postgres.Products;
import com.srkr.accounts.domain.model.postgres.Transactions;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;

@Component
public class TransactionsMapper {

	@Autowired
	private AccountsMapper accountsMapper;

	@Autowired
	private HeadersMapper headersMapper;

	@Autowired
	private ProductsMapper productsMapper;
	
	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;

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
	
	public List<Transactions> toPostgresObjectListFromMap(JsonNode transactionObj) {
		List<Transactions> pgTransactionList = new ArrayList<Transactions>();
		Transactions pgTransactions = null;		
		JsonNode productList = transactionObj.get("productInfo");
		if (productList.isArray()) {
			for (int i = 0; i < productList.size(); i++) {
				
				pgTransactions = new Transactions();
				JsonNode product = productList.get(i);
				System.out.println(product);
				pgTransactions.setUserId(1); // get user id from spring security principal
				pgTransactions.setUserName("admin@admin.com");// get user name from spring security principal
				pgTransactions.setTransaction_number(postgresTransactionsRepository.getNextSequenceValue());
				pgTransactions.setDepartment_id(1); //populate this from UI
				pgTransactions.setDepartment_name("Computer Science");
				pgTransactions.setLine_item_number(i + 1);
				pgTransactions.setName(product.get("name").textValue());
				pgTransactions.setQuantity(product.get("quantity").asInt());
				pgTransactions.setPrice(product.get("price").asDouble());
				pgTransactions.setAmount((double) (product.get("quantity").asInt() * product.get("price").asInt()));
				pgTransactions.setDateupdated(new Date());
				Accounts accounts = new Accounts();
				accounts.setId(6l);
				pgTransactions.setAccounts(accounts);
				Headers headers = new Headers();
				headers.setId(1l);
				pgTransactions.setHeaders(headers);
				Products products = new Products();
				products.setId(4l);
				pgTransactions.setProducts(products);
				pgTransactionList.add(pgTransactions);
			}
		}

		return pgTransactionList;

	}

}
