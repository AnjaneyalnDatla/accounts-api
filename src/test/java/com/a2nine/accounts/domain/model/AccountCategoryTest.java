package com.a2nine.accounts.domain.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.a2nine.accounts.domain.model.AccountCategory;

public class AccountCategoryTest {
	
	@Test
	public void testAccountCategory() {
		AccountCategory accountsCategory = new AccountCategory("AccountCategoryName","AccountCategory Desccription");
		assertNotNull(accountsCategory);
		assertEquals("AccountCategoryName", accountsCategory.name());
		assertEquals("AccountCategory Desccription", accountsCategory.description());
	}
}
