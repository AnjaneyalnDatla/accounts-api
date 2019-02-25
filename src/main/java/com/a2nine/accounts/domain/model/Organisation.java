package com.a2nine.accounts.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Organisation {

	private String name;

	private String code;

	@JsonCreator
	public Organisation(@JsonProperty("name") String name, @JsonProperty("code") String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String name() {
		return name;
	}

	public String code() {
		return code;
	}

}
