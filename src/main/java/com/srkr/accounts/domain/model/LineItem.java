package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LineItem extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 274915459774854721L;

	private Long id;

	private Integer transaction_number;

	private Integer line_item_number;

	private Products products;

	private String name;

	private Integer quantity;

	private Double price;

	private Double amount;

	private Date dateupdated;

	@JsonCreator
	public LineItem(@JsonProperty("id") Long id, @JsonProperty("transaction_number") Integer transaction_number,
			@JsonProperty("line_item_number") Integer line_item_number, @JsonProperty("products") Products products,
			@JsonProperty("name") String name, @JsonProperty("quantity") Integer quantity,
			@JsonProperty("price") Double price, @JsonProperty("amount") Double amount,
			@JsonProperty("dateupdated") Date dateupdated) {
		super();
		this.id = id;
		this.transaction_number = transaction_number;
		this.line_item_number = line_item_number;
		this.products = products;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
		this.dateupdated = dateupdated;
	}

	public Long id() {
		return id;
	}

	public Integer transaction_number() {
		return transaction_number;
	}

	public Integer line_item_number() {
		return line_item_number;
	}

	public Products products() {
		return products;
	}

	public String name() {
		return name;
	}

	public Integer quantity() {
		return quantity;
	}

	public Double price() {
		return price;
	}

	public Double amount() {
		return amount;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date dateupdated() {
		return dateupdated;
	}

}
