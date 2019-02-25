package com.a2nine.accounts.domain.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Contacts extends AssertionConcern implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9076213918969754442L;

	private Long id;
	private String supplementalId;
	private Boolean isCompany;
	private String companyName;
	private String firstName;
	private String middleName;
	private String lastName;
	private String cellPhone;
	private String homePhone;
	private String officePhone;
	private String faxNumber;
	private String emailAddress;
	private String streetAddress;
	private String city;
	private State state;
	private Country country;
	private String postalCode;
	private String landMark;
	private String additionalComments;
	private String idType;
	private String idNumber;
	private String designation;
	private Double current_balance;
	private Date balance_updated_date;
	private Set<Document> documents;
	private Organisation organisation;

	public Contacts(Long id) {
		super();
		this.id = id;
	}

	@JsonCreator
	public Contacts(@JsonProperty("id") Long id, @JsonProperty("supplementalId") String supplementalId,
			@JsonProperty("isCompany") Boolean isCompany, @JsonProperty("companyName") String companyName,
			@JsonProperty("firstName") String firstName, @JsonProperty("middleName") String middleName,
			@JsonProperty("lastName") String lastName, @JsonProperty("cellPhone") String cellPhone,
			@JsonProperty("homePhone") String homePhone, @JsonProperty("officePhone") String officePhone,
			@JsonProperty("faxNumber") String faxNumber, @JsonProperty("emailAddress") String emailAddress,
			@JsonProperty("streetAddress") String streetAddress, @JsonProperty("city") String city,
			@JsonProperty("state") String state, @JsonProperty("country") String country,
			@JsonProperty("postalCode") String postalCode, @JsonProperty("landMark") String landMark,
			@JsonProperty("additionalComments") String additionalComments, @JsonProperty("idType") String idType,
			@JsonProperty("idNumber") String idNumber, @JsonProperty("designation") String designation,
			@JsonProperty("current_balance") Double current_balance,
			@JsonProperty("balance_updated_date") Date balance_updated_date,
			@JsonProperty("documents") Set<Document> documents,
			@JsonProperty("organisation") Organisation organisation) {
		super();
		this.id = id;
		this.supplementalId = supplementalId;
		this.isCompany = isCompany;
		this.companyName = companyName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.cellPhone = cellPhone;
		this.homePhone = homePhone;
		this.officePhone = officePhone;
		this.faxNumber = faxNumber;
		this.emailAddress = emailAddress;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = State.valueOfAbbreviation(state);
		this.country = Country.valueOfAbbreviation(country);
		this.postalCode = postalCode;
		this.landMark = landMark;
		this.additionalComments = additionalComments;
		this.idType = idType;
		this.idNumber = idNumber;
		this.designation = designation;
		this.current_balance = current_balance;
		this.balance_updated_date = balance_updated_date;
		this.documents = documents;
		this.organisation = organisation;
	}

	public Long id() {
		return id;
	}

	public String supplementalId() {
		return supplementalId;
	}

	public Boolean getIsCompany() {
		return isCompany;
	}

	public String companyName() {
		return companyName;
	}

	public String firstName() {
		return firstName;
	}

	public String middleName() {
		return middleName;
	}

	public String lastName() {
		return lastName;
	}

	public String cellPhone() {
		return cellPhone;
	}

	public String homePhone() {
		return homePhone;
	}

	public String officePhone() {
		return officePhone;
	}

	public String faxNumber() {
		return faxNumber;
	}

	public String emailAddress() {
		return emailAddress;
	}

	public String streetAddress() {
		return streetAddress;
	}

	public String city() {
		return city;
	}

	public State state() {
		return state;
	}

	public Country country() {
		return country;
	}

	public String postalCode() {
		return postalCode;
	}

	public String landMark() {
		return landMark;
	}

	public String additionalComments() {
		return additionalComments;
	}

	public String idType() {
		return idType;
	}

	public String idNumber() {
		return idNumber;
	}

	public String designation() {
		return designation;
	}

	public Double current_balance() {
		return current_balance;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date balance_updated_date() {
		return balance_updated_date;
	}

	public Set<Document> documents() {
		if (null == documents)
			return new HashSet<>();
		return documents;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	public Organisation organisation() {
		return this.organisation;
	}

}
