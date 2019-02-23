package com.srkr.accounts.domain.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Products extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3651997606636911234L;

	private Long id;
	private String name;
	private Organisation organisation;

	public Products(String name) {
		super();
		this.name = name;
	}

	@JsonCreator
	public Products(@JsonProperty("id") Long id, @JsonProperty("name") String name,
			@JsonProperty("organisation") Organisation organisation) {
		super();
		this.id = id;
		this.name = name;
		this.organisation = organisation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Organisation organisation() {
		return this.organisation;
	}

}
