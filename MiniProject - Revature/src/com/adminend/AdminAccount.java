package com.adminend;

import java.io.Serializable;
/**
 * @author Amr Hosny
 * Class cotains all Admin info and setters/ getters for the Admin infos
 *
 */
 
public class AdminAccount implements Serializable {

	private static final long serialVersionUID = 9145177158758752L;

	private String Name;
	// private transient int ID;
	private String UserName;
	private String Password;

	public AdminAccount(String name, String username, String pw) {

		super();
		Name = name;
		// ID = id;
		UserName = username;
		Password = pw;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

}
