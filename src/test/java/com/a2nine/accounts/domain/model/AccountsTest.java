package com.a2nine.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.a2nine.accounts.domain.model.AccountCategory;
import com.a2nine.accounts.domain.model.AccountTypes;
import com.a2nine.accounts.domain.model.Accounts;

public class AccountsTest {
	@Test
	public void TestAccounts() {
		Accounts accounts = new Accounts("Accounts Name", "Accounts Description", 
								new AccountTypes("AccountTypesName", 
									new AccountCategory("AccountCategoryName", "AccountCategoryDescription"), 
										"AccountTypesDescription"));
		assertNotNull(accounts);
		assertNotNull(accounts.account_type());
		assertNotNull(accounts.account_type().accountCategory());
		
		assertEquals("Accounts Name", accounts.name());
		assertEquals("Accounts Description", accounts.description());
		assertEquals("AccountTypesName", accounts.account_type().name());
		assertEquals("AccountTypesDescription", accounts.account_type().description());
		assertEquals("AccountCategoryName", accounts.account_type().accountCategory().name());
		assertEquals("AccountCategoryDescription", accounts.account_type().accountCategory().description());
	}
}
