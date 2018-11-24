package com.srkr.accounts.domain.model.postgres;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transactions")
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "transaction_number")
	private Integer transaction_number;

	@Column(name = "department_id")
	private Integer department_id;

	@Column(name = "department_name")
	private String department_name;

	@Column(name = "line_item_no")
	private Integer line_item_no;

	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "account_id", referencedColumnName = "id")
	private Accounts accounts;

	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Products products;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "price")
	private Double price;

	@Column(name = "amount")
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "headers_id", referencedColumnName = "id")
	private Headers headers;
	
	@Column(name = "dateupdated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateupdated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLine_item_number() {
		return line_item_no;
	}

	public void setLine_item_number(Integer line_item_no) {
		this.line_item_no = line_item_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Headers getHeaders() {
		return headers;
	}

	public void setHeaders(Headers headers) {
		this.headers = headers;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTransaction_number() {
		return transaction_number;
	}

	public void setTransaction_number(Integer transaction_number) {
		this.transaction_number = transaction_number;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public Integer getLine_item_no() {
		return line_item_no;
	}

	public void setLine_item_no(Integer line_item_no) {
		this.line_item_no = line_item_no;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public Date getDateupdated() {
		return dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

}
