package com.a2nine.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.a2nine.accounts.domain.model.AccountCategory;
import com.a2nine.accounts.domain.model.AccountTypes;

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
