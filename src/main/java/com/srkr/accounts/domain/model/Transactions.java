package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
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

	@JsonCreator
	public Transactions(@JsonProperty("id") Long id, @JsonProperty("header") Headers headers,
			@JsonProperty("accounts") Accounts accounts, @JsonProperty("transaction_number") Integer transaction_number,
			@JsonProperty("user_id") Integer userId, @JsonProperty("user_name") String userName,
			@JsonProperty("departmentId") Integer departmentId, @JsonProperty("departmentName") String departmentName,
			@JsonProperty("dateupdated") Date dateupdated,@JsonProperty("lineItems")Set<LineItem> lineItems) {
		super();
		this.id = id;
		this.user_id = userId;
		this.user_name = userName;
		this.transaction_number = transaction_number;
		this.accounts = accounts;
		this.header = headers;
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
