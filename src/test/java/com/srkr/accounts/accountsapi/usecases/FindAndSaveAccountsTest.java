package com.srkr.accounts.accountsapi.usecases;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.AccountBalances;
import com.srkr.accounts.domain.model.AccountTypes;
import com.srkr.accounts.domain.model.Accounts;
import com.srkr.accounts.domain.model.Contacts;
import com.srkr.accounts.usecases.FindAndSaveAccounts;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindAndSaveAccountsTest {

	@Autowired
	private FindAndSaveAccounts findAndSaveAccounts;

	private List<Accounts> accountsList;

	@Before
	public void setUp() {
		this.accountsList = new ArrayList<>();
		List<AccountBalances> accountBalances = new ArrayList<>();
		accountBalances.add(new AccountBalances(100d, new Date(2016, 06, 06), 200d, new Date(2017, 06, 06), 1));
		Accounts accounts = new Accounts("TEST Recievables Accounts", "Test", new AccountTypes(2l), new Contacts(5l),
				accountBalances);
		accountsList.add(accounts);
	}

	@Test
	public void findAll() {
		List<Accounts> accounts = findAndSaveAccounts.findAllAccounts();
		assertNotNull(accounts);
	}

	@After
	public void destroy() {

	}

}
