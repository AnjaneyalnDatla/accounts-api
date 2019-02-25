package com.a2nine.accounts.domain.model.postgres;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "transactions", schema = "public")
public class Transactions implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2761309527104883365L;
	private Long id;
	private Integer transactionNumber;
	private Double originalAmount;
	private Double pendingAmount;
	private Contacts contact;
	private String contactName;
	private TransactionTypes transactionType;
	private String transactionTypeName;
	private TransactionStatus transactionStatus;
	private String transactionStatusName;
	private Integer userId;
	private String userName;
	private Integer departmentId;
	private String departmentName;
	private Date dateupdated;
	private Date creationdate;
	private Date dueDate;
	private Date deliveryDate;
	private String orgName;
	private String orgcode;

	private Set<LineItem> lineItems = new HashSet<>();
	private Set<Bill> bills = new HashSet<>();

	public Transactions() {
	}

	public Transactions(Integer transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public Transactions(Long id, Integer transactionNumber, Double originalAmount, Double pendingAmount,
			Contacts contact, TransactionTypes transactionType, TransactionStatus transactionStatus, Integer userId,
			String userName, Integer departmentId, String departmentName, Date dateupdated, Date creationdate,
			Date dueDate, Date deliveryDate, Set<LineItem> lineItems, Set<Bill> bills, String orgName, String orgcode) {
		this.id = id;
		this.transactionNumber = transactionNumber;
		this.originalAmount = originalAmount;
		this.pendingAmount = pendingAmount;
		this.contact = contact;
		this.contactName = contact.getLastname();
		this.transactionType = transactionType;
		this.transactionTypeName = transactionType.getName();
		this.transactionStatus = transactionStatus;
		this.transactionStatusName = transactionStatus.getValue();
		this.userId = userId;
		this.userName = userName;
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.dateupdated = dateupdated;
		this.creationdate = creationdate;
		this.dueDate = dueDate;
		this.deliveryDate = deliveryDate;
		this.lineItems = lineItems;
		this.bills = bills;
		this.orgName = orgName;
		this.orgcode = orgcode;
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

	@Column(name = "transaction_number", nullable = false)
	public Integer getTransactionNumber() {
		return this.transactionNumber;
	}

	public void setTransactionNumber(Integer transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dateupdated", length = 35)
	public Date getDateupdated() {
		return this.dateupdated;
	}

	public void setDateupdated(Date dateupdated) {
		this.dateupdated = dateupdated;
	}

	@Column(name = "original_amount", nullable = false)
	public Double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(Double originalAmount) {
		this.originalAmount = originalAmount;
	}

	@Column(name = "pending_balance", nullable = false)
	public Double getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(Double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact_id")
	public Contacts getContact() {
		return contact;
	}

	public void setContact(Contacts contact) {
		this.contact = contact;
	}

	@Column(name = "contact_name", nullable = false)
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "transactiontype_id")
	public TransactionTypes getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionTypes transactionType) {
		this.transactionType = transactionType;
	}

	@Column(name = "transactiontype_name", nullable = false)
	public String getTransactionTypeName() {
		return this.transactionTypeName;
	}

	public void setTransactionTypeName(String transactionTypeName) {
		this.transactionTypeName = transactionTypeName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "transaction_status_id")
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	@Column(name = "transaction_status_name", nullable = false)
	public String getTransactionStatusName() {
		return transactionStatusName;
	}

	public void setTransactionStatusName(String transactionStatusName) {
		this.transactionStatusName = transactionStatusName;
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

	@Column(name = "creation_date")
	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	@Column(name = "due_date")
	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "delivery_date")
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transactions", orphanRemoval = true)
	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transactions", orphanRemoval = true)
	public Set<Bill> getBills() {
		return bills;
	}

	public void setBills(Set<Bill> bills) {
		this.bills = bills;
	}

	@Column(name = "orgname")
	public String getOrgName() {
		return this.orgName;
	}

	@Column(name = "orgcode")
	public String getOrgcode() {
		return this.orgcode;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

}
