package com.srkr.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.postgres.Accounts;
import com.srkr.accounts.domain.model.postgres.HeaderTypes;
import com.srkr.accounts.domain.model.postgres.Headers;
import com.srkr.accounts.domain.model.postgres.LineItem;
import com.srkr.accounts.domain.model.postgres.Products;
import com.srkr.accounts.domain.model.postgres.Transactions;
import com.srkr.accounts.domain.model.repositories.PostgresHeadersRepository;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostgresTransactionsRepositoryTest {

	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;
	@Autowired
	PostgresHeadersRepository postgresHeadersRepository;

	private Transactions transactions;

	@Before
	public void setUp() {
		this.transactions = new Transactions();
		this.transactions.setUserId(1);
		this.transactions.setUserName("Bunny");
		this.transactions.setDepartmentId(1);
		this.transactions.setDepartmentName("civil");
		Set<LineItem> lineItems = new HashSet<>();
		LineItem item = new LineItem();
		item.setLine_item_number(1);
		item.setProducts(new Products(1l, "Invoice"));
		item.setName("Test 1");
		item.setQuantity(1);
		item.setPrice(20d);
		item.setAmount(20d);
		lineItems.add(item);
		this.transactions.setLineItems(lineItems);
		Accounts accounts = new Accounts();
		accounts.setId(1);
		this.transactions.setAccounts(accounts);
		Headers headers = new Headers();
		headers.setAccounts(accounts);
		headers.setHeaderdate(new Date(2018, 11, 20));
		headers.setHeadernumber(1);
		HeaderTypes headerTypes = new HeaderTypes();
		headerTypes.setId(1l);
		headerTypes.setName("Invoice");
		headers.setHeaderTypes(headerTypes);
		this.transactions.setHeaders(headers);
	}

	@Test
	public void findAllTransactions() {
		List<Transactions> transactions = postgresTransactionsRepository.findAll();
		assertNotNull(transactions);
	}

	@Test
	public void findAllTransactionsByUsername() {
		List<Transactions> transactions = postgresTransactionsRepository.findByUserName("Bunny");
		assertNotNull(transactions);
	}

	@Test
	public void saveTransactions() {
		Integer transaction_number = this.postgresTransactionsRepository.getNextSequenceValue().intValue();
		this.transactions.setTransactionNumber(transaction_number);
		this.transactions.getLineItems().forEach((trans) -> {
			trans.setTransaction_number(transaction_number);
		});
		this.transactions = postgresTransactionsRepository.save(this.transactions);
		assertNotNull(transactions);
	}

	@Test
	public void getNextSeqValue() {
		Integer sequence = postgresTransactionsRepository.getNextSequenceValue().intValue();
		assertNotNull(sequence);
	}

	@After
	public void destroy() {
		if (this.transactions.getId() != null)
			this.postgresTransactionsRepository.delete(this.transactions);
	}

}
