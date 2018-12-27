package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.AccountCategory;
import com.srkr.accounts.domain.model.postgres.AccountTypes;
import com.srkr.accounts.domain.model.postgres.Accounts;

@Component
public class AccountsMapper {

	public Accounts toPostgresObject(com.srkr.accounts.domain.model.Accounts accounts) {
		if (accounts == null) {
			return null;
		}
		Accounts pgAccounts = new Accounts();
		pgAccounts.setId(accounts.id().intValue());
		pgAccounts.setName(accounts.name());
		pgAccounts.setDescription(null != accounts.description() ? accounts.description() : "DEFAULT");

		AccountTypes accountType = new AccountTypes();
		if (accounts.account_type() != null) {
			accountType.setId(accounts.account_type().id());
			accountType.setDescription(accounts.account_type().description());
			accountType.setName(accounts.account_type().name());

			AccountCategory account_category = new AccountCategory();
			account_category.setId(accounts.account_type().accountCategory().id());
			account_category.setDescription(accounts.account_type().accountCategory().description());
			account_category.setName(accounts.account_type().accountCategory().name());
			accountType.setAccountCategory(account_category);
		}

		pgAccounts.setAccountTypes(accountType);
		pgAccounts.setCurrentBalance(accounts.currentBalance());
		return pgAccounts;
	}

	public com.srkr.accounts.domain.model.Accounts toDomainObject(Accounts pgAccounts) {
		if (pgAccounts == null) {
			return null;
		}
		com.srkr.accounts.domain.model.AccountTypes acctType = null;
		if (pgAccounts.getAccountTypes().getId() != null) {
			acctType = new com.srkr.accounts.domain.model.AccountTypes(pgAccounts.getAccountTypes().getId(),
					pgAccounts.getAccountTypes().getName(),
					new com.srkr.accounts.domain.model.AccountCategory(
							pgAccounts.getAccountTypes().getAccountCategory().getId(),
							pgAccounts.getAccountTypes().getAccountCategory().getName(),
							pgAccounts.getAccountTypes().getAccountCategory().getDescription()),
					pgAccounts.getAccountTypes().getDescription());
		}

		com.srkr.accounts.domain.model.Accounts accounts = new com.srkr.accounts.domain.model.Accounts(
				pgAccounts.getId().longValue(), pgAccounts.getName(), pgAccounts.getDescription(), acctType,
				pgAccounts.getCurrentBalance());

		return accounts;
	}

	public List<com.srkr.accounts.domain.model.Accounts> toListDomainObjects(List<Accounts> accounts) {
		List<com.srkr.accounts.domain.model.Accounts> listOfAccounts = new ArrayList<com.srkr.accounts.domain.model.Accounts>();
		accounts.forEach(c -> {
			listOfAccounts.add(toDomainObject(c));
		});
		return listOfAccounts;
	}
}
