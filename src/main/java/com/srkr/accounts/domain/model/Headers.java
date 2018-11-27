package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Headers extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5835995173434301348L;

	private Long id;

	private Integer headernumber;

	private Date headerdate;

	private HeaderTypes headerTypes;
	
	private Accounts accounts;
	
	private Date dateupdated;

	@JsonCreator
	public Headers(@JsonProperty("id")Long id,@JsonProperty("headernumber")Integer headernumber,
			@JsonProperty("headerdate")Date headerdate,@JsonProperty("headerTypes")HeaderTypes headerTypes,
			@JsonProperty("accounts")Accounts accounts,
			@JsonProperty("dateupdated")Date dateupdated) {
		super();
		this.id = id;
		this.headernumber = headernumber;
		this.headerdate = headerdate;
		this.headerTypes = headerTypes;
		this.accounts = accounts;
		this.dateupdated = dateupdated;
	}

	public Headers(Integer headernumber, Date headerdate, HeaderTypes headerTypes) {
		super();
		this.headernumber = headernumber;
		this.headerdate = headerdate;
		this.headerTypes = headerTypes;
	}

	public Long id() {
		return id;
	}

	public Integer headernumber() {
		return headernumber;
	}

	public Date headerdate() {
		return headerdate;
	}

	public HeaderTypes headerTypes() {
		return headerTypes;
	}
	
	public Accounts accounts() {
		return accounts;
	}
	
	public Date dateupdated() {
		return dateupdated;
	}
}
