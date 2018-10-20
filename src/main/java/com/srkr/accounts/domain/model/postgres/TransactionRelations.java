package com.srkr.accounts.domain.model.postgres;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_relations")
public class TransactionRelations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "department_id")
	private Integer departmentId;

	@Column(name = "transaction_id")
	private Integer transaction_id;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "contact_id", referencedColumnName = "id")
	private Contacts contact_id;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "header_id", referencedColumnName = "id")
	private Headers header_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Contacts getContact_id() {
		return contact_id;
	}

	public void setContact_id(Contacts contact_id) {
		this.contact_id = contact_id;
	}

	public Headers getHeader_id() {
		return header_id;
	}

	public void setHeader_id(Headers header_id) {
		this.header_id = header_id;
	}
	
	

}
