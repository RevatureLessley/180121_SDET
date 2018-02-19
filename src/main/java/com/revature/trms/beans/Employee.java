package com.revature.trms.beans;

public class Employee {
	private String empId;
	private String ssn;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String phone;
	private String email;
	private String deptNum;
	private String supervisorId;
	private int jobRef;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private String birthday;
	
	public Employee() {
	     this.firstName="kamel";
	     this.lastName="berrani";
	     this.empId="1";
		
	}
	public Employee(String empId, String ssn, String firstName, String lastName, String username, String password,
			String phone, String email, String deptNum, String supervisorId, int jobRef, String address, String city,
			String state, String zipCode, String birthdate) {
		super();
		this.empId = empId;
		this.ssn = ssn;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.deptNum = deptNum;
		this.supervisorId = supervisorId;
		this.jobRef = jobRef;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.birthday = birthdate;
	}
    
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDeptNum() {
		return deptNum;
	}
	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public int getJobRef() {
		return jobRef;
	}
	public void setJobRef(int jobRef) {
		this.jobRef = jobRef;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getBirthdate() {
		return birthday;
	}
	public void setBirthdate(String birthdate) {
		this.birthday = birthdate;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", ssn=" + ssn + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", password=" + password + ", phone=" + phone + ", email=" + email
				+ ", deptNum=" + deptNum + ", supervisorId=" + supervisorId + ", jobRef=" + jobRef + ", address="
				+ address + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode + ", birthday=" + birthday
				+ "]";
	}
	
	

}
