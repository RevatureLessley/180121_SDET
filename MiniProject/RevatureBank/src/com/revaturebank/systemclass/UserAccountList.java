package com.revaturebank.systemclass;

import java.sql.SQLException;
import java.util.ArrayList;
import com.revaturebank.dao.UserAccountDAOClass;


import org.apache.log4j.Logger;

public class UserAccountList {
	//Using a logger in this.class
	final static Logger logger=Logger.getLogger(UserAccountList.class);
	//private data consist of HashMap to load data with key username 
	private static UserAccountList usersList;
	private UserAccountDAOClass uaDao =new UserAccountDAOClass();
	//public method to instantiate an object and only allows one in entire application
	public static UserAccountList getUserAccountList() {
    	if (usersList==null)
			try {
				usersList=new UserAccountList();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
    	return usersList;
    		
    }
	//private constructor to load data from the files
	private UserAccountList() throws SQLException{
	
	}
	
	public boolean search(String username) throws SQLException {
		return uaDao.search(username);
	}
	//add user by UserAcount
	public boolean add(UserAccount ua){
		 return uaDao.requestUserAccount(ua.getUsername(), ua.getPassword(),ua.getSsNumber(), ua.getFirsName(), ua.getLastName(),
                 ua.getEmail(), ua.getPhone());
		}
	//add user to the by parameter
	public boolean add(String ssNumber, String firstName, String lastName, String phone, String email,
		 String username, String password) {
		return uaDao.requestUserAccount(username, password,ssNumber,firstName, lastName,email, phone);       
		  }
	public boolean edit(String username ,String ssn, String firstName, String lastName, String email, String phone) {
	     return uaDao.updateUserAccountPI(username, ssn, firstName, lastName, email, phone);
           }
		//update username and password
	public boolean edit(String u,String username,String password ) {
		   
		       return uaDao.updateUserAccuntUP(u,username, password);
		  }
	
		
	public UserAccount authenticate(String username,String password) throws Exception{
		return uaDao.authenticate(username, password);

		}
	public void transactions(String aNum) {
		try {
			
			ArrayList<Transaction> m=uaDao.getTransactionsAccount(aNum);
			
			for( Transaction trans : m) {
				System.out.println("\n   |"+trans.getTrans_id()+"  |  "+trans.getTrans_date()+"  |  "+trans.getAmount()+"  |  "+trans.getDescription());
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
		
			
	
		
	
    
}
