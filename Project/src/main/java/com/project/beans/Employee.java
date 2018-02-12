package com.project.beans;

/**
 * Basic Employee information collection 
 * @author haris
 *
 */
public class Employee {
	
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String title;
	private String super_firstName;
	private String super_lastName;
	private String email;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSuper_firstName() {
		return super_firstName;
	}
	public void setSuper_firstName(String super_firstName) {
		this.super_firstName = super_firstName;
	}
	public String getSuper_lastName() {
		return super_lastName;
	}
	public void setSuper_lastName(String super_lastName) {
		this.super_lastName = super_lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Employee(String firstName, String lastName, String userName, String password, String title,
			String super_firstName, String super_lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.title = title;
		this.super_firstName = super_firstName;
		this.super_lastName = super_lastName;
		this.email = email;
	}
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", password="
				+ password + ", title=" + title + ", super_firstName=" + super_firstName + ", super_lastName="
				+ super_lastName + ", email=" + email + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((super_firstName == null) ? 0 : super_firstName.hashCode());
		result = prime * result + ((super_lastName == null) ? 0 : super_lastName.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (super_firstName == null) {
			if (other.super_firstName != null)
				return false;
		} else if (!super_firstName.equals(other.super_firstName))
			return false;
		if (super_lastName == null) {
			if (other.super_lastName != null)
				return false;
		} else if (!super_lastName.equals(other.super_lastName))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}
