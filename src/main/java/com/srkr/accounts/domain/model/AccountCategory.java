package com.srkr.accounts.domain.model;

import java.io.Serializable;

public class AccountCategory extends AssertionConcern implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2684990329086993917L;

	private Long id;

	private String name;

	private String description;

	public AccountCategory(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public AccountCategory(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Long id() {
		return this.id;
	}

	public String name() {
		return this.name;
	}

	public String description() {
		return this.description;
	}

}
