package com.a2nine.accounts.domain.model.postgres;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document", schema = "public")
public class Document implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2708205933478961694L;

	private Long id;

	private Long documentReferencerNumber;

	private String documentName;

	private String documentLink;

	private Date documentUploadedDate;

	public Document() {

	}

	public Document(Long id, Long documentReferencerNumber, String documentName, String documentLink,
			Date documentUploadedDate) {
		super();
		this.id = id;
		this.documentReferencerNumber = documentReferencerNumber;
		this.documentName = documentName;
		this.documentLink = documentLink;
		this.documentUploadedDate = documentUploadedDate;
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

	@Column(name = "documentreferencernumber", unique = true, nullable = false)
	public Long getDocumentReferencerNumber() {
		return documentReferencerNumber;
	}

	public void setDocumentReferencerNumber(Long documentReferencerNumber) {
		this.documentReferencerNumber = documentReferencerNumber;
	}

	@Column(name = "documentname", unique = true, nullable = false)
	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	@Column(name = "documentlink", unique = true, nullable = false)
	public String getDocumentLink() {
		return documentLink;
	}

	public void setDocumentLink(String documentLink) {
		this.documentLink = documentLink;
	}

	@Column(name = "documentuploadeddate", unique = true, nullable = false)
	public Date getDocumentUploadedDate() {
		return documentUploadedDate;
	}

	public void setDocumentUploadedDate(Date documentUploadedDate) {
		this.documentUploadedDate = documentUploadedDate;
	}

}
