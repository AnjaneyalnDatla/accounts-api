package com.a2nine.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.a2nine.accounts.domain.model.postgres.AccountCategory;
import com.a2nine.accounts.domain.model.postgres.AccountTypes;
import com.a2nine.accounts.domain.model.postgres.Accounts;
import com.a2nine.accounts.domain.model.repositories.PostgresAccountsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostgresAccountsRepositoryTest {

	@Autowired
	private PostgresAccountsRepository accountsRepository;

	private List<Accounts> accountsList;

	private List<Accounts> accountsDeleteList = new ArrayList<>();

	@Before
	public void setUp() {
		this.accountsList = new ArrayList<>();

		Accounts pgAccounts = new Accounts();
		pgAccounts.setId(17);
		pgAccounts.setName("TEST Recievables Accounts");
		pgAccounts.setDescription("TEST");

		AccountTypes accountType = new AccountTypes();
		accountType.setId(1l);
		accountType.setDescription("DEFAULT");
		accountType.setName("BANK");

		AccountCategory account_category = new AccountCategory();
		account_category.setId(1l);
		account_category.setDescription("DEFAULT");
		account_category.setName("ASSET");
		accountType.setAccountCategory(account_category);

		pgAccounts.setAccountTypes(accountType);
		pgAccounts.setCurrentBalance(20000.00);
		pgAccounts.setDateupdated(new Date());
		pgAccounts.setIsActive(true);
		pgAccounts.setOrgcode("DEFAULT");
		pgAccounts.setOrgName("DEFAULT");
		accountsList.add(pgAccounts);
	}

	@Test
	public void findAll() {
		List<Accounts> accounts = accountsRepository.findByOrgcode("DEFAULT");
		assertNotNull(accounts);
	}
	
	@Test
	public void save() {
		accountsList.forEach((acc) -> {
			accountsDeleteList.add(this.accountsRepository.save(acc));
		});
		assertTrue(accountsDeleteList.size() > 0);
	}


	@Test
	public void findAccountsByName() {
		Accounts accounts = accountsRepository.findByNameAndOrgcode("HDFC CHECKINGS", "DEFAULT");
		assertNotNull(accounts);
	}


	/*@After
	public void after() {
		accountsDeleteList.forEach(acc -> {
			this.accountsRepository.delete(acc);
		});
	}*/

}
