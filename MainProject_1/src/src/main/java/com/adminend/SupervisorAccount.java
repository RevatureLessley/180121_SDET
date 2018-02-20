package com.adminend;

public class SupervisorAccount {

	private Integer SupervisorID;
	private String FirstName;
	private String LastName;
	private String UserName;
	private String Password;
	private String Phone;
	private String Email;
	private Integer ReferenceID;
	
	public SupervisorAccount(Integer supervisorID, String firstName, String lastName, String userName, String password,
			String phone, String email, Integer referenceID) {
		super();
		SupervisorID = supervisorID;
		FirstName = firstName;
		LastName = lastName;
		UserName = userName;
		Password = password;
		Phone = phone;
		Email = email;
		ReferenceID = referenceID;
	}

	public SupervisorAccount(String userName, String password, Integer supervisorID, Integer referenceID) {
		
		super();
		SupervisorID = supervisorID;
		UserName = userName;
		Password = password;
		ReferenceID = referenceID;
	}
	
	public SupervisorAccount(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}

	public Integer getSupervisorID() {
		return SupervisorID;
	}

	public void setSupervisorID(Integer supervisorID) {
		SupervisorID = supervisorID;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Integer getReferenceID() {
		return ReferenceID;
	}

	public void setReferenceID(Integer referenceID) {
		ReferenceID = referenceID;
	}
	
	
	
}
