package com.bank.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.beans.Customer;
import com.bank.beans.User;
import com.bank.dao.CustomerDao;
import com.bank.dao.CustomerDaoImp;
import com.bank.main.Driver;

public class CustomerService {
	
	final static Logger logger = Logger.getLogger(Driver.class);
	static CustomerDao Cdao = new CustomerDaoImp();
	
	public static void displayCustomer(){
		//Display existing customers 
		List<Customer> customers = Cdao.getAllCustomer();	
		System.out.println("\n====LIST OF CUSTOMERS====");
		for(Customer u: customers){
			System.out.println(u.getUserName()+" Account Value "+u.getAmount());	
		}
		System.out.println("\n");
	}
	
	public static Customer getCustomerInfo(User u) {
		// Return customer info from matching user
		for(Customer c: Cdao.getAllCustomer()) {
			if(c.getUserName().equals(u.getUserName())) {
				return c;
			}
		}
		return null;
	}

	
	public static void addAmount(Customer p) {
		
		
	}

	public static void updateAmount(Customer p) {
		// // add amount or subtract amount in customer account
		if(Cdao.update(p)){
			System.out.println(p.getUserName()+" amount updated to "+p.getAmount()+"\n");
			logger.info(p.getUserName()+" new amount is "+ p.getAmount());
		}else{
			System.out.println(p.getUserName()+" amount update failed");
			logger.info(p.getUserName()+" amount update failed");
		}
	}

}
