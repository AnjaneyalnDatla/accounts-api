package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.AccountCategory;
import com.srkr.accounts.domain.model.postgres.AccountTypes;

@Component
public class AccountTypesMapper {

	public AccountTypes toPostgresObject(com.srkr.accounts.domain.model.AccountTypes accountTypes) {
		AccountTypes accountType = new AccountTypes();
		accountType.setId(accountTypes.id());
		accountType.setDescription(accountTypes.description());
		accountType.setName(accountTypes.name());

		AccountCategory account_category = new AccountCategory();
		account_category.setId(accountTypes.accountCategory().id());
		account_category.setDescription(accountTypes.accountCategory().description());
		account_category.setName(accountTypes.accountCategory().name());
		accountType.setAccountCategory(account_category);
		return accountType;

	}

	public com.srkr.accounts.domain.model.AccountTypes toDomainObject(AccountTypes pgAccountTypes) {
		return new com.srkr.accounts.domain.model.AccountTypes(pgAccountTypes.getId(), pgAccountTypes.getName(),
				new com.srkr.accounts.domain.model.AccountCategory(pgAccountTypes.getAccountCategory().getId(),
						pgAccountTypes.getAccountCategory().getName(),
						pgAccountTypes.getAccountCategory().getDescription()),
				pgAccountTypes.getDescription());
	}

	public List<com.srkr.accounts.domain.model.AccountTypes> toListDomainObjects(List<AccountTypes> accountsTypes) {
		List<com.srkr.accounts.domain.model.AccountTypes> listOfAccountTypes = new ArrayList<com.srkr.accounts.domain.model.AccountTypes>();
		accountsTypes.forEach(c -> {
			listOfAccountTypes.add(toDomainObject(c));
		});
		return listOfAccountTypes;
	}
}
