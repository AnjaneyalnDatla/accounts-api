package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.Set;

public class Transactions extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3143036145899180010L;

	private Long id;

	private Integer user_id;

	private Integer transaction_number;

	private String user_name;

	private Set<LineItem> lineItems;

	private Integer departmentId;

	private String departmentName;

	private Accounts accounts;

	private Headers header;

	public Transactions(Long id, Integer user_id, Integer transaction_number, String user_name, Accounts accounts,
			Headers header, Set<LineItem> lineItems, Integer departmentId, String departmentName) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.transaction_number = transaction_number;
		this.accounts = accounts;
		this.header = header;
		this.lineItems = lineItems;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public Transactions(Integer user_id, Integer transaction_number, String user_name, Accounts accounts,
			Headers header, Set<LineItem> lineItems, Integer departmentId, String departmentName) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.transaction_number = transaction_number;
		this.accounts = accounts;
		this.header = header;
		this.lineItems = lineItems;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}

	public Long id() {
		return id;
	}

	public Integer user_id() {
		return user_id;
	}

	public String user_name() {
		return user_name;
	}

	public Integer transaction_number() {
		return transaction_number;
	}

	public Set<LineItem> lineItems() {
		return lineItems;
	}

	public Accounts accounts() {
		return accounts;
	}

	public Headers header() {
		return header;
	}

	public Integer departmentId() {
		return departmentId;
	}

	public String departmentName() {
		return departmentName;
	}

}
