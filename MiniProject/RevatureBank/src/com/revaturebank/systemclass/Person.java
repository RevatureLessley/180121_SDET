package com.revaturebank.systemclass;
// an abstract class person to be extended to all the people used in the application 
public abstract class Person {
     private String ssNumber;
     private String firsName;
     private String lastName;
     private String phone;
     private String email;
     
     
     protected Person() {
    	 
     }


	protected Person(String ssNumber, String firsName, String lastName, String phone, String email) {
		super();
		this.ssNumber = ssNumber;
		this.firsName = firsName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}


	public String getSsNumber() {
		return ssNumber;
	}


	public void setSsNumber(String ssNumber) {
		this.ssNumber = ssNumber;
	}


	public String getFirsName() {
		return firsName;
	}


	public void setFirsName(String firsName) {
		this.firsName = firsName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
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
     


}
