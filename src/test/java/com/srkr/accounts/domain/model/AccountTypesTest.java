package com.srkr.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AccountTypesTest {

	@Test
	public void TestAccountTypes() {
		AccountTypes accountTypes = new AccountTypes("AccountTypesName", 
				new AccountCategory("AccountCategoryName", "AccountCategoryDescription"), "AccountTypesDescription");
		assertNotNull(accountTypes);
		assertNotNull(accountTypes.accountCategory());
		assertEquals("AccountTypesName", accountTypes.name());
		assertEquals("AccountTypesDescription", accountTypes.description());
		assertEquals("AccountCategoryName", accountTypes.accountCategory().name());
		assertEquals("AccountCategoryDescription", accountTypes.accountCategory().description());
	}
}
