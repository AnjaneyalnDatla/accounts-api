package com.srkr.accounts.domain.model.postgres;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TransactionRelationsId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6026372987930605384L;
	private Long transactionId;
	private Long contactId;
	private Long headerId;

	public TransactionRelationsId() {
	}

	public TransactionRelationsId(Long transactionId, Long contactId, Long headerId) {
		this.transactionId = transactionId;
		this.contactId = contactId;
		this.headerId = headerId;
	}

	@Column(name = "transaction_id", nullable = false)
	public Long getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	@Column(name = "contact_id", nullable = false)
	public Long getContactId() {
		return this.contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	@Column(name = "header_id", nullable = false)
	public Long getHeaderId() {
		return this.headerId;
	}

	public void setHeaderId(Long headerId) {
		this.headerId = headerId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TransactionRelationsId))
			return false;
		TransactionRelationsId castOther = (TransactionRelationsId) other;

		return (this.getTransactionId() == castOther.getTransactionId())
				&& (this.getContactId() == castOther.getContactId()) && (this.getHeaderId() == castOther.getHeaderId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getTransactionId().intValue();
		result = 37 * result + this.getContactId().intValue();
		result = 37 * result + this.getHeaderId().intValue();
		return result;
	}

}
