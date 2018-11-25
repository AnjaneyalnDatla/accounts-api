package com.srkr.accounts.domain.model.postgres;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "transaction_relations", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class TransactionRelations implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 805482306469467123L;
	private TransactionRelationsId id;
	private Contacts contacts;
	private Headers headers;
	private Transactions transactions;
	private Long id_1;
	private Integer userId;
	private Integer departmentId;
	private Date dateupdated;

	public TransactionRelations() {
	}

	public TransactionRelations(TransactionRelationsId id, Contacts contacts, Headers headers,
			Transactions transactions, Long id_1, Integer userId, Integer departmentId) {
		this.id = id;
		this.contacts = contacts;
		this.headers = headers;
		this.transactions = transactions;
		this.id_1 = id_1;
		this.userId = userId;
		this.departmentId = departmentId;
	}

	public TransactionRelations(TransactionRelationsId id, Contacts contacts, Headers headers,
			Transactions transactions, Long id_1, Integer userId, Integer departmentId, Date dateupdated) {
		this.id = id;
		this.contacts = contacts;
		this.headers = headers;
		this.transactions = transactions;
		this.id_1 = id_1;
		this.userId = userId;
		this.departmentId = departmentId;
		this.dateupdated = dateupdated;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "transactionId", column = @Column(name = "transaction_id", nullable = false)),
			@AttributeOverride(name = "contactId", column = @Column(name = "contact_id", nullable = false)),
			@AttributeOverride(name = "headerId", column = @Column(name = "header_id", nullable = false)) })
	public TransactionRelationsId getId() {
		return this.id;
	}

	public void setId(TransactionRelationsId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id", nullable = false, insertable = false, updatable = false)
	public Contacts getContacts() {
		return this.contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "header_id", nullable = false, insertable = false, updatable = false)
	public Headers getHeaders() {
		return this.headers;
	}

	public void setHeaders(Headers headers) {
		this.headers = headers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "transaction_id", nullable = false, insertable = false, updatable = false)
	public Transactions getTransactions() {
		return this.transactions;
	}

	public void setTransactions(Transactions transactions) {
		this.transactions = transactions;
	}

	@Column(name = "id", unique = true, nullable = false)
	public Long getId_1() {
		return this.id_1;
	}

	public void setId_1(Long id_1) {
		this.id_1 = id_1;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "department_id", nullable = false)
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateupdated", length = 35)
	public Date getDateupdated() {
		return this.dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

}
