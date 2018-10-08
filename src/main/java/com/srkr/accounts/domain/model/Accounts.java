package com.srkr.accounts.domain.model;

public class Accounts {

	private Long id;

	private String name;

	private AccountTypes account_type;

	private Accounts parent_account;

	public Accounts(Long id, String name, AccountTypes account_type, Accounts parent_account) {
		super();
		this.id = id;
		this.name = name;
		this.account_type = account_type;
		this.parent_account = parent_account;
	}
	
	public Accounts(String name, AccountTypes account_type, Accounts parent_account) {
		super();
		this.name = name;
		this.account_type = account_type;
		this.parent_account = parent_account;
	}


	public Long id() {
		return id;
	}

	public String name() {
		return name;
	}

	public AccountTypes account_type() {
		return account_type;
	}

	public Accounts parent_account() {
		return parent_account;
	}

}
