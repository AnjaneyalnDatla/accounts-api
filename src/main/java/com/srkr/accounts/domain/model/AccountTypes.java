package com.srkr.accounts.domain.model;

public class AccountTypes {

	private Long id;

	private String name;

	private AccountCategory account_type;

	private String description;

	public AccountTypes(Long id, String name, AccountCategory account_type, String description) {
		super();
		this.id = id;
		this.name = name;
		this.account_type = account_type;
		this.description = description;
	}

	public AccountTypes(String name, AccountCategory account_type, String description) {
		super();
		this.name = name;
		this.account_type = account_type;
		this.description = description;
	}

	public Long id() {
		return id;
	}

	public String name() {
		return name;
	}

	public AccountCategory account_type() {
		return account_type;
	}

	public String description() {
		return description;
	}

}
