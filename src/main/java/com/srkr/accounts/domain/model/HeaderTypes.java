package com.srkr.accounts.domain.model;

public class HeaderTypes {

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
