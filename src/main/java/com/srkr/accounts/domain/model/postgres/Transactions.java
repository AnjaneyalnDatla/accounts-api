package com.srkr.accounts.domain.model.postgres;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transactions", schema = "public")
@NamedEntityGraphs({
    @NamedEntityGraph(name="transactions.lineItems", attributeNodes = {
            @NamedAttributeNode("lineItems")
    })
})
public class Transactions implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2761309527104883365L;
	private Long id;
	private Headers headers;
	private Accounts accounts;
	private Integer transactionNumber;
	private Integer userId;
	private String userName;
	private Integer departmentId;
	private String departmentName;
	private Set<LineItem> lineItems = new HashSet<>();
	private Date dateupdated;
	private Set<TransactionRelations> transactionRelationses = new HashSet<TransactionRelations>(0);

	public Transactions() {
	}

	public Transactions(Long id, Accounts accounts, Integer transactionNumber, Integer userId, String userName,
			Integer departmentId, Set<LineItem> lineItems, String departmentName, String name) {
		this.id = id;
		this.accounts = accounts;
		this.transactionNumber = transactionNumber;
		this.userId = userId;
		this.userName = userName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.lineItems = lineItems;
	}

	public Transactions(Long id, Headers headers, Accounts accounts, Integer transactionNumber, Integer userId,
			String userName, Integer departmentId, String departmentName, Date dateupdated,
			Set<TransactionRelations> transactionRelationses, Set<LineItem> lineItems) {
		this.id = id;
		this.headers = headers;
		this.accounts = accounts;
		this.transactionNumber = transactionNumber;
		this.userId = userId;
		this.userName = userName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.dateupdated = dateupdated;
		this.lineItems = lineItems;
		this.transactionRelationses = transactionRelationses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name = "headers_id")
	public Headers getHeaders() {
		return this.headers;
	}

	public void setHeaders(Headers headers) {
		this.headers = headers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	public Accounts getAccounts() {
		return this.accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	@Column(name = "transaction_number", nullable = false)
	public Integer getTransactionNumber() {
		return this.transactionNumber;
	}

	public void setTransactionNumber(Integer transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	@Column(name = "user_id", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "user_name", nullable = false)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "department_id", nullable = false)
	public Integer getDepartmentId() {
		return this.departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	@Column(name = "department_name", nullable = false)
	public String getDepartmentName() {
		return this.departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "transactions", cascade = CascadeType.ALL)
	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateupdated", length = 35)
	public Date getDateupdated() {
		return this.dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "transactions")
	public Set<TransactionRelations> getTransactionRelationses() {
		return this.transactionRelationses;
	}

	public void setTransactionRelationses(Set<TransactionRelations> transactionRelationses) {
		this.transactionRelationses = transactionRelationses;
	}

}
