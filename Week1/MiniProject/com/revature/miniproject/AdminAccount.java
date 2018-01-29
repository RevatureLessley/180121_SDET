package com.revature.miniproject;

public class AdminAccount {

	private final String ADMINUSER= "admin";
	private String ADMINPW = "admin";
	private AccountFile f = new AccountFile();
	
	public AdminAccount(){
	}
	public String getADMIN() {
		return ADMINUSER;
	}

	public boolean checkAdmin(String username, String pw)
	{
		if(ADMINUSER.equals(username) & ADMINPW.equals(pw))
			return true;
		else 
			return false;
	}
	public void setADMINPW(String aDMINPW) {
		ADMINPW = aDMINPW;
	}
	public void verifyAccount(BankAccount d)
	{	
		d.setApproval(true); 	
	}

	
}
