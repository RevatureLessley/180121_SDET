package com.project1.beans;

public class Account {
	String email;
    String password;
    String isBenCo;
    String isDirSup;
    String isDepHead;
    
	public Account(String email, String username, String password, String isBenCo, String isDirSup, String isDepHead) {
		super();
		this.email = email;
		this.password = password;
		this.isBenCo = isBenCo;
		this.isDirSup = isDirSup;
		this.isDepHead = isDepHead;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsBenCo() {
		return isBenCo;
	}

	public void setIsBenCo(String isBenCo) {
		this.isBenCo = isBenCo;
	}

	public String getIsDirSup() {
		return isDirSup;
	}

	public void setIsDirSup(String isDirSup) {
		this.isDirSup = isDirSup;
	}

	public String getIsDepHead() {
		return isDepHead;
	}

	public void setIsDepHead(String isDepHead) {
		this.isDepHead = isDepHead;
	}

	@Override
	public String toString() {
		return "Account [email=" + email + ", password=" + password + ", isBenCo=" + isBenCo
				+ ", isDirSup=" + isDirSup + ", isDepHead=" + isDepHead + "]";
	}
	
	
}
