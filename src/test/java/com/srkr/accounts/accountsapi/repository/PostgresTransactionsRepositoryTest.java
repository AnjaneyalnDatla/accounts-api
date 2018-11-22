package com.srkr.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.postgres.Accounts;
import com.srkr.accounts.domain.model.postgres.Headers;
import com.srkr.accounts.domain.model.postgres.Transactions;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PostgresTransactionsRepositoryTest {

	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;

	private Transactions transactions;
	private List<Transactions> listOfTransactions;

	@Before
	public void setUp() {
		this.transactions = new Transactions();
		this.transactions.setUserId(1);
		this.transactions.setUserName("Bunny");
		this.transactions.setDepartment_id(1);
		this.transactions.setDepartment_name("civil");
		this.transactions.setLine_item_no(1);
		this.transactions.setName("Test Meals");
		Accounts accounts = new Accounts();
		accounts.setId(1l);
		this.transactions.setAccounts(accounts);
		this.transactions.setQuantity(2);
		this.transactions.setPrice(150.00d);
		this.transactions.setAmount(300.00d);
		Headers headers = new Headers();
		headers.setId(1l);
		this.transactions.setHeaders(headers);
	}

	@Test
	public void saveTransactions() {
		this.transactions.setTransaction_number(this.postgresTransactionsRepository.getNextSequenceValue().intValue());
		this.transactions = postgresTransactionsRepository.save(this.transactions);
		assertNotNull(transactions);
	}
	@Test
	public void savePurchaseTransactions() {
		Integer transactionNumber = this.postgresTransactionsRepository.getNextSequenceValue().intValue();
		this.listOfTransactions.add(this.transactions);
		
		this.transactions = postgresTransactionsRepository.save(this.transactions);
		assertNotNull(transactions);
	}
	
	@Test
	public void getNextSeqValue() {
		Long sequence = postgresTransactionsRepository.getNextSequenceValue();
		assertNotNull(sequence);
	}


	@After
	public void destroy() {
		postgresTransactionsRepository.delete(this.transactions);

	}
	
}
