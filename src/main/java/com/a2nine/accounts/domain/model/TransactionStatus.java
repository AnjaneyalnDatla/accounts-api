package com.a2nine.accounts.domain.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionStatus extends AssertionConcern implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 3902742312508439193L;

	private Long id;

	private String value;


	@JsonCreator
	public TransactionStatus(@JsonProperty("id") Long id, @JsonProperty("value") String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Long id() {
		return id;
	}

	public String value() {
		return value;
	}

}
