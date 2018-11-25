package com.srkr.accounts.domain.model.postgres;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "line_items", schema = "public")
public class LineItem implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 274915459774854721L;

	private Long id;
	private Integer transaction_number;
	private Transactions transactions;
	private Integer line_item_number;
	private Products products;
	private String name;
	private Integer quantity;
	private Double price;
	private Double amount;
	private Date dateupdated;

	public LineItem() {
	}

	public LineItem(Long id, Integer transaction_number, Transactions transactions, Integer line_item_number,
			Products products, String name, Integer quantity, Double price, Double amount, Date dateupdated) {
		super();
		this.id = id;
		this.transaction_number = transaction_number;
		this.transactions = transactions;
		this.line_item_number = line_item_number;
		this.products = products;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.amount = amount;
		this.dateupdated = dateupdated;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "transaction_number")
	public Integer getTransaction_number() {
		return transaction_number;
	}

	public void setTransaction_number(Integer transaction_number) {
		this.transaction_number = transaction_number;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaction_id")
	public Transactions getTransactions() {
		return transactions;
	}

	public void setTransactions(Transactions transactions) {
		this.transactions = transactions;
	}

	@Column(name = "line_item_no")
	public Integer getLine_item_number() {
		return line_item_number;
	}

	public void setLine_item_number(Integer line_item_number) {
		this.line_item_number = line_item_number;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "quantity")
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "price")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "amount")
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "dateupdated")
	public Date getDateupdated() {
		return dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

}
