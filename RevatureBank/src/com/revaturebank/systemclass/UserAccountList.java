package com.revaturebank.systemclass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class UserAccountList implements Exportable{
	//Using a logger in this.class
	final static Logger logger=Logger.getLogger(UserAccountList.class);
	//private data consist of HashMap to load data with key username 
	private static UserAccountList usersList;
	private HashMap <String, UserAccount> users;
    
	//public method to instantiate an object and only allows one in entire application
	public static UserAccountList getUserAccountList() {
    	if (usersList==null)
    	   usersList=new UserAccountList();
    	return usersList;
    		
    }
	//private constructor to load data from the files
	private UserAccountList() {
		this.users = new HashMap<>();
		this.dataLoad();
	}
	//get a collection 
    public HashMap<String,UserAccount> getUsers() {
    	return this.users;
    }
	//search through the accounts by username(key)
	public UserAccount search(String key) throws Exception{
		try {
		    return users.get(key);
		}
		catch (Exception e){
		    throw new Exception(); 
		      }
		}
	//add user to the list by UserAcount
	public boolean add(UserAccount UA){
		 return users.putIfAbsent(UA.getUsername(), UA) == null;
		}
	//add user to the list by parameter
	public boolean add(String ssNumber, String firsName, String lastName, String phone, String email,
		 String username, String password, Double amount) {
		   UserAccount user=new UserAccount(ssNumber, firsName, lastName, phone, email,
				   username, password, amount);
		   return users.put(username, user)==null;       
		  }
		//activate
	public boolean edit(String username,String accountNumber, boolean bool ) {
		   try{ 
		        if (users.get(username)!=null){
		                users.get(username).setActrive(bool);
		                users.get(username).setAccountNumber(accountNumber);
		                 return true;       
		            }
		    }
		   catch (Exception obj){
		    obj.getStackTrace();
		    }
		  return false;
		}
	// to delete an account
	public boolean remove(String username) throws Exception{
		   try{
		       return users.remove(username)!=null;
		   }
		   catch (Exception e){
		    throw new Exception();
		      }    
		}
	//to clear data
	public void clear() throws Exception{
		 try{ users.clear();  
		     }
		 catch (Exception e){
		    throw new Exception(" Error!!:" + e.toString() );
		      }
	}

	
	public UserAccount authenticate(String username,String password) throws Exception{
		try{    
		   dataLoad();
		   return users.get(username);
		   
		   }
		catch (Exception e){
		    throw new Exception(e.toString() );
		    }

		}
	
		@Override
		public void dataLoad() {
			try{ BufferedReader rb = new BufferedReader(new FileReader("src\\com\\revaturebank\\data\\UserAccountData.txt"));
            String line;
            line=rb.readLine();
            while(line!=null){
               String [] arrline=line.split(",");
               UserAccount ua= new UserAccount();
               ua.setSsNumber(arrline[0]);
               ua.setFirsName(arrline[1]);
               ua.setLastName(arrline[2]);
               ua.setEmail(arrline[3]);
               ua.setPhone(arrline[4]);
               ua.setAccountNumber(arrline[5]);
               ua.setUsername(arrline[6]);
               ua.setPassword(arrline[7]);
               ua.setBalanceAccount(Double.parseDouble(arrline[8]));
               ua.setActrive(Boolean.parseBoolean(arrline[9]));
               try {
				this.add(ua);
			    } 
               catch (Exception e) {
            	logger.error("reading in the file errer");
				e.printStackTrace();
			    }
                line=rb.readLine();
              }
              rb.close();
           }
           catch (IOException ex){
        	   logger.warn("the file does not exist");
            try{PrintWriter out = new PrintWriter("src\\com\\revaturebank\\data\\UserAccountData.txt");
              out.close();
              logger.info("New file is created");
           } catch (FileNotFoundException e){
              e.getStackTrace();
             }
          
             }catch (IndexOutOfBoundsException ex ) {
            	 logger.fatal("Data in the file are unorganized!! (extra commas");
            	 System.out.println("please avoid comma into your entries: "+ex.getStackTrace());
             }
			
		}

		@Override
		public void dataSave() {
			try {
		        PrintWriter output;
		        output = new PrintWriter( new BufferedWriter( new FileWriter("src\\com\\revaturebank\\data\\UserAccountData.txt")));
		        users.entrySet().stream().forEach((user) -> {
		            output.println(users.get(user.getKey()).getSsNumber()+"," +users.get(user.getKey()).getFirsName()+
		            	   	"," +  users.get(user.getKey()).getLastName()+"," +users.get(user.getKey()).getEmail()+
		            		"," +  users.get(user.getKey()).getPhone()   +"," + users.get(user.getKey()).getAccountNumber() +
		            		"," +  users.get(user.getKey()).getUsername()+"," + users.get(user.getKey()).getPassword() +
		            	"," +  users.get(user.getKey()).getBalanceAccount()+"," +  users.get(user.getKey()).isActrive());
		        });
		        output.close();
		    } catch (IOException ex) {
		    	logger.error("In saving to the file (IOE) "+ex.getMessage());
		        ex.getStackTrace();
		    }
			
		}
		
			  
				
		
	
		
	
    
}