package com.revaturebank.systemclass;

import java.util.Random;

//admin class  
public class Admin extends Person {
	
	private String adminId;
	private String username;
	private String password;
	
	
	public Admin() {
		
	}
	
	public Admin(String adminId, String username, String password) {
		super();
		this.adminId = adminId;
		this.username = username;
		this.password = password;
	}
	

	public Admin(String ssNumber, String firsName, String lastName, String phone, String email,
			 String adminId,String username, String password) {
		super(ssNumber, firsName, lastName, phone, email);
		this.adminId = adminId ;
		
		this.username = username;
		this.password = password;
	}

	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
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
	
	public Admin authentication(String uName,String pWord){
        return this;
        
    }
    	
	

}
