package com.adminend;

public class DeptHeadAccount {

	private Integer DeptHeadID;
	private String FirstName;
	private String LastName;
	private String UserName;
	private String Password;
	private String Phone;
	private String Email;
	private Integer DeptID;
	
	public DeptHeadAccount(Integer deptHeadId, String firstName, String lastName, String userName, String password,
			String phone, String email, Integer deptId) {
		super();
		DeptHeadID = deptHeadId;
		FirstName = firstName;
		LastName = lastName;
		UserName = userName;
		Password = password;
		Phone = phone;
		Email = email;
		DeptID = deptId;
	}

	public DeptHeadAccount(String userName, String password, Integer deptHeadId, Integer deptId) {
		
		super();
		DeptHeadID = deptHeadId;
		UserName = userName;
		Password = password;
		DeptID = deptId;
	}
	
	public DeptHeadAccount(String userName, String password) {
		super();
		UserName = userName;
		Password = password;
	}


	public Integer getDeptHeadID() {
		return DeptHeadID;
	}

	public void setDeptHeadID(Integer deptHeadID) {
		DeptHeadID = deptHeadID;
	}

	public Integer getDeptID() {
		return DeptID;
	}

	public void setDeptID(Integer deptID) {
		DeptID = deptID;
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
