package com.srkr.accounts.domain.model;

import java.io.Serializable;

public class HeaderTypes extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3418726870965527429L;

	private Long id;

	private String name;

	private String description;

	public HeaderTypes(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public HeaderTypes(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Long id() {
		return id;
	}

	public String name() {
		return name;
	}

	public String description() {
		return description;
	}
}
