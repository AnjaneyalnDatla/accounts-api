package com.srkr.accounts.domain.model;

import java.io.Serializable;

public class TransactionRelations extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1594393306651383208L;

	private Long id;

	private Integer userId;

	private Integer departmentId;

	private Transactions transactions;

	private Contacts contacts;

	private Headers headers;

	/**
	 * @param id
	 * @param userId
	 * @param departmentId
	 * @param transactions
	 * @param contacts
	 * @param headers
	 */
	public TransactionRelations(Long id, Integer userId, Integer departmentId, Transactions transactions,
			Contacts contacts, Headers headers) {
		super();
		this.id = id;
		this.userId = userId;
		this.departmentId = departmentId;
		this.transactions = transactions;
		this.contacts = contacts;
		this.headers = headers;
	}

	public Long id() {
		return id;
	}

	public Integer userId() {
		return userId;
	}

	public Integer departmentId() {
		return departmentId;
	}

	public Transactions transactions() {
		return transactions;
	}

	public Contacts contacts() {
		return contacts;
	}

	public Headers headers() {
		return headers;
	}

}
