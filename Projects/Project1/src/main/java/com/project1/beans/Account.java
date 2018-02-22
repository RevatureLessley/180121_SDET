package com.project1.beans;

public class Account {
	String email;
    String password;
    int isBenCo;
    int isDirSup;
    int isDepHead;
    
	public Account(String email, String username, String password, int isBenCo, int isDirSup, int isDepHead) {
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

	public int getIsBenCo() {
		return isBenCo;
	}

	public void setIsBenCo(int isBenCo) {
		this.isBenCo = isBenCo;
	}

	public int getIsDirSup() {
		return isDirSup;
	}

	public void setIsDirSup(int isDirSup) {
		this.isDirSup = isDirSup;
	}

	public int getIsDepHead() {
		return isDepHead;
	}

	public void setIsDepHead(int isDepHead) {
		this.isDepHead = isDepHead;
	}

	@Override
	public String toString() {
		return "Account [email=" + email + ", password=" + password + ", isBenCo=" + isBenCo
				+ ", isDirSup=" + isDirSup + ", isDepHead=" + isDepHead + "]";
	}
	
	
}
