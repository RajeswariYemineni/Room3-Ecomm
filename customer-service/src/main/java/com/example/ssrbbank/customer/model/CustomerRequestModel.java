package com.example.ssrbbank.customer.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CustomerRequestModel {
		
	@NotNull(message="First Name should not be null.")
	private String firstName;
	
	@NotNull(message="Last Name should not be null.")
	private String lastName;
	
	@NotNull(message="First Name should not be null.")
	private String password;
	
	@NotNull(message="First Name should not be null.")
	@Email
	private String email;
	
	@NotNull(message="First Name should not be null.")
	private String phoneNum;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dob;
	
	public CustomerRequestModel() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	

}
