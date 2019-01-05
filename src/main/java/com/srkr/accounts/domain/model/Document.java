package com.srkr.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Document extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8836441401305038392L;

	private Long id;

	private Long documentReferencerNumber;

	private String documentName;

	private String documentLink;

	private Date documentUploadedDate;

	@JsonCreator
	public Document(@JsonProperty("id") Long id,
			@JsonProperty("documentReferencerNumber") Long documentReferencerNumber,
			@JsonProperty("documentName") String documentName, @JsonProperty("documentLink") String documentLink,
			@JsonProperty("documentUploadedDate") Date documentUploadedDate) {
		super();
		this.id = id;
		this.documentReferencerNumber = documentReferencerNumber;
		this.documentName = documentName;
		this.documentLink = documentLink;
		this.documentUploadedDate = documentUploadedDate;
	}

	public Long id() {
		return id;
	}

	public Long documentReferencerNumber() {
		return documentReferencerNumber;
	}

	public String documentName() {
		return documentName;
	}

	public String documentLink() {
		return documentLink;
	}

	public Date documentUploadedDate() {
		return documentUploadedDate;
	}

}
