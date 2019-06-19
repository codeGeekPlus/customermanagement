package uk.com.atos.ho.customer.model;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
	
	@JsonPropertyDescription("id")
	@Valid
	@NotBlank
	private String id;
	
	@JsonPropertyDescription("firstName")
	@Valid
	@NotBlank
	private String firstName;
	
	@JsonPropertyDescription("surName")
	@Valid
	@NotBlank
	private String surname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
