package com.srkr.accounts.domain.model.postgres;

import org.springframework.stereotype.Component;

@Component
public class AccountsMapper {

	public Accounts toPostgresObject(com.srkr.accounts.domain.model.Accounts accounts) {
		if (accounts == null) {
			return null;
		}
		Accounts pgAccounts = new Accounts();
		pgAccounts.setId(accounts.id());
		pgAccounts.setName(accounts.name());
		pgAccounts.setParent_account(toPostgresObject(accounts.parent_account()));

		AccountTypes accountType = new AccountTypes();
		accountType.setId(accounts.account_type().id());
		accountType.setDescription(accounts.account_type().description());
		accountType.setName(accounts.account_type().name());

		AccountCategory account_category = new AccountCategory();
		account_category.setId(accounts.account_type().account_type().id());
		account_category.setDescription(accounts.account_type().account_type().description());
		account_category.setName(accounts.account_type().account_type().name());
		accountType.setAccount_type(account_category);

		pgAccounts.setAccount_type(accountType);

		return pgAccounts;
	}

	public com.srkr.accounts.domain.model.Accounts toDomainObject(Accounts pgAccounts) {
		if (pgAccounts == null) {
			return null;
		}
		com.srkr.accounts.domain.model.Accounts accounts = new com.srkr.accounts.domain.model.Accounts(
				pgAccounts.getId(), pgAccounts.getName(),
				new com.srkr.accounts.domain.model.AccountTypes(pgAccounts.getAccount_type().getId(),
						pgAccounts.getAccount_type().getName(),
						new com.srkr.accounts.domain.model.AccountCategory(
								pgAccounts.getAccount_type().getAccount_type().getId(),
								pgAccounts.getAccount_type().getAccount_type().getName(),
								pgAccounts.getAccount_type().getAccount_type().getDescription()),
						pgAccounts.getAccount_type().getDescription()),
				toDomainObject(pgAccounts.getParent_account()));

		return accounts;
	}
}
