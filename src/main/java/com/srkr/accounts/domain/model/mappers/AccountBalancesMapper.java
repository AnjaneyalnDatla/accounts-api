package com.srkr.accounts.domain.model.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.srkr.accounts.domain.model.postgres.AccountBalances;

@Component
public class AccountBalancesMapper {

	public List<AccountBalances> toPostgresObjects(
			List<com.srkr.accounts.domain.model.AccountBalances> accountBalancesList) {
		List<AccountBalances> accountBalances = new ArrayList<>();
		accountBalancesList.forEach((accountBalance) -> {
			accountBalances.add(toPostgresObject(accountBalance));
		});
		return accountBalances;
	}

	public List<com.srkr.accounts.domain.model.AccountBalances> toDomainObjects(
			List<AccountBalances> accountBalancesList) {
		List<com.srkr.accounts.domain.model.AccountBalances> accountBalances = new ArrayList<>();
		accountBalancesList.forEach((accountBalance) -> {
			accountBalances.add(toDomainObject(accountBalance));
		});
		return accountBalances;
	}

	private AccountBalances toPostgresObject(com.srkr.accounts.domain.model.AccountBalances accountBalances) {
		AccountBalances pgAccountBalances = new AccountBalances();
		pgAccountBalances.setId(accountBalances.id());
		pgAccountBalances.setBeginning_balance(accountBalances.beginning_balance());
		pgAccountBalances.setBeginning_balance_date(accountBalances.beginning_balance_date());
		pgAccountBalances.setCurrent_balance(accountBalances.current_balance());
		pgAccountBalances.setCurrent_balance_date(accountBalances.current_balance_date());
		pgAccountBalances.setUpdatedBy(accountBalances.updatedBy());
		return pgAccountBalances;

	}

	private com.srkr.accounts.domain.model.AccountBalances toDomainObject(
			com.srkr.accounts.domain.model.postgres.AccountBalances pgAccountBalances) {
		return new com.srkr.accounts.domain.model.AccountBalances(pgAccountBalances.getId(),
				pgAccountBalances.getBeginning_balance(), pgAccountBalances.getBeginning_balance_date(),
				pgAccountBalances.getCurrent_balance(), pgAccountBalances.getCurrent_balance_date(),
				pgAccountBalances.getUpdatedBy());

	}

}
