package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.sql.Date;

public class Headers extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5835995173434301348L;

	private Long id;

	private Integer headernumber;

	private Date headerdate;

	private HeaderTypes headerTypes;

	public Headers(Long id, Integer headernumber, Date headerdate, HeaderTypes headerTypes) {
		super();
		this.id = id;
		this.headernumber = headernumber;
		this.headerdate = headerdate;
		this.headerTypes = headerTypes;
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

}
