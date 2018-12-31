package com.srkr.accounts.accountsapi.usecases;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.srkr.accounts.domain.model.Accounts;
import com.srkr.accounts.domain.model.Contacts;
import com.srkr.accounts.domain.model.LineItem;
import com.srkr.accounts.domain.model.Products;
import com.srkr.accounts.domain.model.TransactionStatus;
import com.srkr.accounts.domain.model.TransactionTypes;
import com.srkr.accounts.domain.model.Transactions;
import com.srkr.accounts.domain.model.mappers.AccountsMapper;
import com.srkr.accounts.domain.model.mappers.TransactionsMapper;
import com.srkr.accounts.domain.model.repositories.PostgresAccountsRepository;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionTypesRepository;
import com.srkr.accounts.domain.model.repositories.PostgresTransactionsRepository;
import com.srkr.accounts.usecases.FindAndSaveTransactions;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FindAndSaveTransactionsTest {

	@Autowired
	PostgresTransactionsRepository postgresTransactionsRepository;
	@Autowired
	PostgresTransactionTypesRepository postgresTransactionTypesRepository;
	@Autowired
	PostgresAccountsRepository postgresAccountsRepository;

	@Autowired
	TransactionsMapper transactionsMapper;
	@Autowired
	AccountsMapper accountsMapper;

	private Transactions transactions;

	@Before
	public void setUp() {

		Set<LineItem> lineItems = new HashSet<>();
		LineItem item = new LineItem(null, null, 1, new Products(1l,"Test2"), "TEST2", 15, 15.00d, 15.00d, null);
		lineItems.add(item);
		this.transactions = new Transactions(null, null, 1000d, 200d,
				new Contacts(1l),
				new TransactionTypes(1l, "INVOICE", null), new TransactionStatus(1l, "COMPLETE"), 10d, 20d, 30d, 4,
				"admin@admin.com", 1, "CIVIL", null, lineItems, new Date(), new Date(), new Date(),null);

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
	}

	//@After
	public void destroy() {
		if (this.transactions.id() != null)
			this.findAndSaveTransactions.deleteTransaction(this.transactions);
	}

}
