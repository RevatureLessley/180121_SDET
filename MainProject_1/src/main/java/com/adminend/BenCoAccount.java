package com.adminend;

public class BenCoAccount {
	private Integer BenCoID;
	private String FirstName;
	private String LastName;
	private String UserName;
	private String Password;
	private String Phone;
	private String Email;

	
	public BenCoAccount(Integer bencoId, String firstName, String lastName, String userName, String password,
			String phone, String email) {
		super();
		BenCoID = bencoId;
		FirstName = firstName;
		LastName = lastName;
		UserName = userName;
		Password = password;
		Phone = phone;
		Email = email;
		
	}

	public BenCoAccount(String userName, String password, Integer bencoId) {
		
		super();
		BenCoID = bencoId;
		UserName = userName;
		Password = password;
	}
	
	public BenCoAccount(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}

	public Integer getBenCoID() {
		return BenCoID;
	}

	public void setBenCoID(Integer benCoID) {
		BenCoID = benCoID;
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



}