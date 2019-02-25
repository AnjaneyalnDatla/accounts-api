package com.a2nine.accounts.accountsapi.usecases;

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

import com.a2nine.accounts.domain.model.Contacts;
import com.a2nine.accounts.domain.model.LineItem;
import com.a2nine.accounts.domain.model.Organisation;
import com.a2nine.accounts.domain.model.Products;
import com.a2nine.accounts.domain.model.TransactionLog;
import com.a2nine.accounts.domain.model.TransactionStatus;
import com.a2nine.accounts.domain.model.TransactionTypes;
import com.a2nine.accounts.domain.model.Transactions;
import com.a2nine.accounts.domain.model.mappers.AccountsMapper;
import com.a2nine.accounts.domain.model.mappers.TransactionsMapper;
import com.a2nine.accounts.domain.model.repositories.PostgresAccountsRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresTransactionTypesRepository;
import com.a2nine.accounts.domain.model.repositories.PostgresTransactionsRepository;
import com.a2nine.accounts.usecases.FindAndSaveTransactions;

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

	private TransactionLog transactionLog;

	@Before
	public void setUp() {

		Set<LineItem> lineItems = new HashSet<>();
		LineItem item = new LineItem(null, null, 1, new Products(1l, "Test2", new Organisation("DEFAULT", "DEFAULT")),
				"TEST2", 15, 15.00d, 15.00d, null);
		lineItems.add(item);
		this.transactions = new Transactions(null, null, 5000d, 2500d, new Contacts(1l),
				new TransactionTypes(1l, "INVOICE", null), new TransactionStatus(3l, "APPROVAL PENDING"), 10d, 20d, 30d,
				4, "admin@admin.com", 1, "CIVIL", null, lineItems, new Date(), new Date(), new Date(), null, null,
				new Organisation("DEFAULT", "DEFAULT"));

	}

	@Autowired
	private FindAndSaveTransactions findAndSaveTransactions;

	@Test
	public void findAllTransactions() {
		assertNotNull(findAndSaveTransactions.findAllTransactions("DEFAULT"));
	}

	@Test
	public void findAllTransactionsByUsername() {
		assertNotNull(findAndSaveTransactions.findTransactionsByUsername("Bunny", "DEFAULT"));
	}

	@Test
	public void saveTransaction() {
		this.transactions = findAndSaveTransactions.saveTransaction(this.transactions);
		assertNotNull(this.transactions);
	}

	@Test
	public void approveTransaction() {
		this.transactions = findAndSaveTransactions.saveTransaction(this.transactions);
		this.transactionLog = new TransactionLog(null, this.transactions, new TransactionStatus(1l, "COMPLETE"),
				new Date(), "KGollapalli", "Testing");
		this.transactionLog = findAndSaveTransactions.approveTransaction(transactionLog);
		assertNotNull(this.transactionLog);
	}

	@Test
	public void sendEmail() throws Exception {
		findAndSaveTransactions.sendEmail();
	}

	// @After
	public void destroy() {
		if (this.transactions.id() != null)
			this.findAndSaveTransactions.deleteTransaction(this.transactions);
	}

}
