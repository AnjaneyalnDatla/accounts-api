package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Transactions extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3143036145899180010L;

	private Long id;

	private Integer transaction_number;

	private Double paymentAmount;

	private Double pendingAmount;

	private Contacts contact;

	private TransactionTypes transactionType;

	private TransactionStatus transactionStatus;

	private Double tax;

	private Double shipping;

	private Double other;

	private Integer user_id;

	private String user_name;

	private Set<LineItem> lineItems;

	private Integer departmentId;

	private String departmentName;

	private Date creationdate;

	private Date dueDate;

	private Date deliveryDate;

	private Set<Bill> bills;

	@JsonCreator
	public Transactions(@JsonProperty("id") Long id, @JsonProperty("transaction_number") Integer transaction_number,
			@JsonProperty("paymentAmount") Double paymentAmount, @JsonProperty("pendingAmount") Double pendingAmount,
			@JsonProperty("contact") Contacts contacts,
			@JsonProperty("transactionType") TransactionTypes transactionType,
			@JsonProperty("transactionStatus") TransactionStatus transactionStatus, @JsonProperty("tax") Double tax,
			@JsonProperty("shipping") Double shipping, @JsonProperty("other") Double other,
			@JsonProperty("user_id") Integer userId, @JsonProperty("user_name") String userName,
			@JsonProperty("departmentId") Integer departmentId, @JsonProperty("departmentName") String departmentName,
			@JsonProperty("dateupdated") Date dateupdated, @JsonProperty("lineItems") Set<LineItem> lineItems,
			@JsonProperty("creationdate") Date creationdate, @JsonProperty("dueDate") Date dueDate,
			@JsonProperty("deliveryDate") Date deliveryDate, @JsonProperty("bills") Set<Bill> bills) {
		super();
		this.id = id;
		this.user_id = userId;
		this.user_name = userName;
		this.transaction_number = transaction_number;
		this.transactionType = transactionType;
		this.transactionStatus = transactionStatus;
		this.lineItems = lineItems;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.contact = contacts;
		this.tax = tax;
		this.paymentAmount = paymentAmount;
		// default to zero pending amount if no pending amount
		this.pendingAmount = (null != pendingAmount ? pendingAmount : 0d);
		this.creationdate = creationdate;
		this.other = other;
		this.shipping = shipping;
		this.dueDate = dueDate;
		this.deliveryDate = deliveryDate;
		this.bills = bills;
	}

	public Transactions(Integer user_id, Integer transaction_number, String user_name, TransactionTypes transactionType,
			Set<LineItem> lineItems, Integer departmentId, String departmentName) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.transaction_number = transaction_number;
		this.transactionType = transactionType;
		this.lineItems = lineItems;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
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

	public Integer transaction_number() {
		return transaction_number;
	}

	public Set<LineItem> lineItems() {
		if (null == lineItems)
			return new HashSet<>();
		return lineItems;
	}

	public TransactionTypes transactionType() {
		return transactionType;
	}

	public Integer departmentId() {
		return departmentId;
	}

	public String departmentName() {
		return departmentName;
	}

	public Contacts contact() {
		return contact;
	}

	public Double tax() {
		return tax;
	}

	public Double other() {
		return other;
	}

	public Double shipping() {
		return shipping;
	}

	public Double paymentAmount() {
		return paymentAmount;
	}

	public Date creationdate() {
		return creationdate;
	}

	public TransactionStatus transactionStatus() {
		return transactionStatus;
	}

	public Double pendingAmount() {
		return pendingAmount;
	}

	public Date dueDate() {
		return dueDate;
	}

	public Date deliveryDate() {
		return deliveryDate;
	}

	public Set<Bill> bills() {
		if (null == bills)
			return new HashSet<>();
		return bills;
	}

}
