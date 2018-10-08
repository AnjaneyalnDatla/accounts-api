package com.srkr.accounts.domain.model;

public class Transactions {

	private Long id;

	private Integer user_id;

	private String user_name;

	private Integer line_item_number;

	private String name;

	private Accounts accounts;

	private Integer quantity;

	private Double price;

	private Double amount;

	private Headers header;

	public Transactions(Long id, Integer user_id, String user_name, Integer line_item_number, String name,
			Accounts accounts, Integer quantity, Double price, Double amount, Headers header) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.line_item_number = line_item_number;
		this.name = name;
		this.accounts = accounts;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
		this.header = header;
	}

	public Transactions(Integer user_id, String user_name, Integer line_item_number, String name, Accounts accounts,
			Integer quantity, Double price, Double amount, Headers header) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.line_item_number = line_item_number;
		this.name = name;
		this.accounts = accounts;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
		this.header = header;
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

	public Integer line_item_number() {
		return line_item_number;
	}

	public String name() {
		return name;
	}

	public Accounts accounts() {
		return accounts;
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

	public Headers header() {
		return header;
	}

}
