package com.srkr.accounts.accountsapi.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.srkr.accounts.domain.model.postgres.AccountTypes;
import com.srkr.accounts.domain.model.postgres.Accounts;
import com.srkr.accounts.domain.model.postgres.AccountsBalance;
import com.srkr.accounts.domain.model.postgres.Contacts;
import com.srkr.accounts.domain.model.repositories.PostgresAccountsRepository;

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
		Accounts accounts = new Accounts();
		accounts.setName("TEST Recievables Accounts");
		accounts.setDescription("TEST");
		AccountTypes account_type = new AccountTypes();
		account_type.setId(2l);
		accounts.setAccountTypes(account_type);
		Contacts contacts = new Contacts();
		contacts.setId(5l);
		accounts.setContact(contacts);
		AccountsBalance accountBalances = new AccountsBalance();
		accountBalances.setBeginningBalance(100.00d);
		accountBalances.setBeginningBalanceDate(new Date(2016, 12, 12));
		accountBalances.setCurrentBalance(200.d);
		accountBalances.setCurrentBalanceDate(new Date(2018, 12, 12));
		accountBalances.setUpdatedby(1);
		accountBalances.setAccounts(accounts);
		Set<AccountsBalance> balances = new HashSet<>();
		balances.add(accountBalances);
		accounts.setAccountsBalances(balances);
		accountsList.add(accounts);
	}

	@Test
	public void findAll() {
		List<Accounts> accounts = accountsRepository.findAll();
		assertNotNull(accounts);
	}
	
	@Test
	public void findAccountsByName() {
		Accounts accounts = accountsRepository.findByName("SRT ELECTRICALS ACCOUNT RECEIVABLES");
		assertNotNull(accounts);
	}

	@Test
	public void save() {
		accountsList.forEach((acc) -> {
			accountsDeleteList.add(this.accountsRepository.save(acc));
		});
		assertTrue(accountsDeleteList.size() > 0);
	}

	@After
	public void after() {
		accountsDeleteList.forEach(acc -> {
			this.accountsRepository.delete(acc);
		});
	}

}
