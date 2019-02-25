package com.a2nine.accounts.domain.model.postgres;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_status", schema = "public")
public class TransactionStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3902742312508439193L;

	private Long id;

	private String value;

	public TransactionStatus() {
	}

	public TransactionStatus(Long id, String value) {
		this.id = id;
		this.value = value;
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

	@Column(name = "value", nullable = false)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
