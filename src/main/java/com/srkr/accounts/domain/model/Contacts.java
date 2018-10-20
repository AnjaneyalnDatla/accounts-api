package com.srkr.accounts.domain.model;

import java.io.Serializable;

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

	public Contacts() {

	}

	public Contacts(Long id, String supplementalId, Boolean isCompany, String companyName, String firstName,
			String middleName, String lastName, String cellPhone, String homePhone, String officePhone,
			String faxNumber, String emailAddress, String streetAddress, String city, State state, Country country,
			String postalCode, String landMark, String additionalComments, String idType, String idNumber,
			String designation) {
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
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.landMark = landMark;
		this.additionalComments = additionalComments;
		this.idType = idType;
		this.idNumber = idNumber;
		this.designation = designation;
	}

	@JsonProperty("id")
	public Long id() {
		return id;
	}

	@JsonProperty("supplementalId")
	public String supplementalId() {
		return supplementalId;
	}

	@JsonProperty("isCompany")
	public Boolean isCompany() {
		return isCompany;
	}

	@JsonProperty("companyName")
	public String companyName() {
		return companyName;
	}

	@JsonProperty("firstName")
	public String firstName() {
		return firstName;
	}

	@JsonProperty("middleName")
	public String middleName() {
		return middleName;
	}

	@JsonProperty("lastName")
	public String lastName() {
		return lastName;
	}

	@JsonProperty("cellPhone")
	public String cellPhone() {
		return cellPhone;
	}

	@JsonProperty("homePhone")
	public String homePhone() {
		return homePhone;
	}

	@JsonProperty("officePhone")
	public String officePhone() {
		return officePhone;
	}

	@JsonProperty("faxNumber")
	public String faxNumber() {
		return faxNumber;
	}

	@JsonProperty("emailAddress")
	public String emailAddress() {
		return emailAddress;
	}

	@JsonProperty("streetAddress")
	public String streetAddress() {
		return streetAddress;
	}

	@JsonProperty("city")
	public String city() {
		return city;
	}

	@JsonProperty("state")
	public State state() {
		return state;
	}

	@JsonProperty("country")
	public Country country() {
		return country;
	}

	@JsonProperty("postalCode")
	public String postalCode() {
		return postalCode;
	}

	@JsonProperty("landMark")
	public String landMark() {
		return landMark;
	}

	@JsonProperty("additionalComments")
	public String additionalComments() {
		return additionalComments;
	}

	@JsonProperty("idType")
	public String idType() {
		return idType;
	}

	@JsonProperty("idNumber")
	public String idNumber() {
		return idNumber;
	}

	@JsonProperty("designation")
	public String designation() {
		return designation;
	}

}
