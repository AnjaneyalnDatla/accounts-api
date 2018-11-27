package com.srkr.accounts.accountsapi.usecases;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.Accounts;
import com.srkr.accounts.domain.model.HeaderTypes;
import com.srkr.accounts.domain.model.Headers;
import com.srkr.accounts.domain.model.LineItem;
import com.srkr.accounts.domain.model.Products;
import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.domain.model.mappers.AccountsMapper;
import com.srkr.accounts.domain.model.mappers.TransactionsMapper;
import com.srkr.accounts.domain.model.repositories.PostgresAccountsRepository;
import com.srkr.accounts.domain.model.repositories.PostgresHeadersRepository;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;
import com.srkr.accounts.usecases.FindAndSaveTransactions;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FindAndSaveTransactionsTest {

	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;
	@Autowired
	PostgresHeadersRepository postgresHeadersRepository;
	@Autowired
	PostgresAccountsRepository postgresAccountsRepository;

	@Autowired
	TransactionsMapper transactionsMapper;
	@Autowired
	AccountsMapper accountsMapper;

	private Transactions transactions;

	@Before
	public void setUp() {
		Accounts accounts = accountsMapper
				.toDomainObject(postgresAccountsRepository.findByName("SRT ELECTRICALS ACCOUNT RECEIVABLES"));
		Headers headers = new Headers(null, 999, new Date(2018, 11, 20), new HeaderTypes(1l, "Invoice", "Invoice"),
				accounts,null);
		Integer transaction_number = this.postgresTransactionsRepository.getNextSequenceValue().intValue();
		Set<LineItem> lineItems = new HashSet<>();
		LineItem item = new LineItem(null, transaction_number, 1, new Products("Test"), "TEST", 15, 15.00d, 15.00d,
				null);
		lineItems.add(item);
		this.transactions = new Transactions(new Integer(1), transaction_number, "Bunny", accounts, headers, lineItems,
				new Integer(1), "civil");
	}

	@Autowired
	private FindAndSaveTransactions findAndSaveTransactions;

	@Test
	public void findAllTransactions() {
		assertNotNull(findAndSaveTransactions.findAllTransactions());
	}

	@Test
	public void findAllTransactionsByUsername() {
		assertNotNull(findAndSaveTransactions.findTransactionsByUsername("Bunny"));
	}

	@Test
	public void saveTransaction() {
		this.transactions = findAndSaveTransactions.saveTransaction(this.transactions);
		assertNotNull(this.transactions);
		assertNotNull(this.transactions.lineItems());
		assertNotNull(this.transactions.header());
		assertNotNull(this.transactions.accounts());
	}

	@After
	public void destroy() {
		if (this.transactions.id() != null)
			this.findAndSaveTransactions.deleteTransaction(this.transactions);
	}

}
