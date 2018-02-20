package com.userend;

public class AssociateAccount {
	
	
	private Integer AssociateID;
	private String FirstName;
	private String LastName;
	private String UserName;
	private String Password;
	private String Phone;
	private String Email;
	private Integer SupervisorRef;
	private Integer Balance_Available;
	
	
	public AssociateAccount(Integer associate_id, String firstName, String lastName,
							String userName, String password, String phone, String email, Integer supervisor) {
		
		
		super();
		AssociateID = associate_id;
		FirstName = firstName;
		LastName = lastName;
		UserName = userName;
		Password = password;
		Phone = phone;
		Email = email;
		SupervisorRef = supervisor;
		
	}


	/*public AssociateAccount(String firstName, String lastName, String userName, String password, String title,
							String phone, String email, Integer balance_Available) {
		
		super();
		FirstName = firstName;
		LastName = lastName;
		UserName = userName;
		Password = password;
		Title = title;
		Phone = phone;
		Email = email;
		Balance_Available = balance_Available;	
		}*/



	public AssociateAccount(String username, String password, Integer associate_id, Integer balance, Integer supervisor) {
		
		UserName = username;
		Password = password;
		AssociateID = associate_id;
		Balance_Available = balance;
		SupervisorRef = supervisor;

		}
	
	
	public AssociateAccount(String username, String password) {
		
		UserName = username;
		Password = password;
		
		}


	public Integer getBalance_Available() {
		return Balance_Available;
	}


	public void setBalance_Available(Integer balance_Available) {
		Balance_Available = balance_Available;
	}


	public Integer getAssociateID() {
		return AssociateID;
	}


	public void setAssociateID(Integer associateID) {
		AssociateID = associateID;
	}


	public void setSupervisorRef(Integer supervisorRef) {
		SupervisorRef = supervisorRef;
	}


	public Integer getSupervisorRef() {
		return SupervisorRef;
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
