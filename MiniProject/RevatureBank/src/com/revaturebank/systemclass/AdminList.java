package com.revaturebank.systemclass;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.log4j.Logger;

import com.revaturebank.dao.AdminDAOClass;

public class AdminList {
	//logger of this class
	final static Logger logger=Logger.getLogger(UserAccountList.class);
	
	private AdminDAOClass AdDao =new AdminDAOClass();
	public AdminList() {
		}
	
	public Admin authenticate(String username,String password) throws Exception{
		return AdDao.authenticate(username, password);

	}
	// add an Admin to the system
	
    public boolean add(Admin a) {
    	return AdDao.insertAdmin(a.getAdminId(),a.getUsername(), a.getPassword(), a.getSsNumber(),
    			a.getFirsName(), a.getLastName(), a.getEmail(), a.getPhone());
		}
    
		  
	//to approve an account and associate it with account number
	public void activateAccount(String username) {
		
      try {
      UserAccount ua=AdDao.search(username);
      if (ua!=null) {
    	  Random rand=new Random();
          int num=rand.nextInt(10000)+10000;
          String accountNumber="4321"+num;
          ua.setAccountNumber(accountNumber);
          
          if (AdDao.insertUserAccount(ua))
	          System.out.println("....done");
          
               }
      else System.out.println("request not found");
      }catch(SQLException ex) {
    	  ex.getStackTrace();
      }
	}
	public void AllAccountRequest() {
		try {
		ArrayList<UserAccount> m=AdDao.allAccountRequest();
			for (UserAccount user:m) {
                  System.out.println(user.getSsNumber()+"	| " +user.getFirsName()+ "	| " + user.getLastName()+ "	| "+user.getEmail()+
                		  "	| " +  user.getPhone()   + "	| " +  user.getUsername()+ "	| " + user.getPassword() );
        }
		}catch(SQLException ex) {
			ex.getStackTrace();
		}
		}
	public boolean remove(String accountNumber,String username){
		return AdDao.deleteUserAccount(accountNumber, username);
		      
		}
	
	
}
