package com.bank.dao;

import java.util.List;

import com.bank.beans.Customer;

public interface CustomerDao {
	
	public List<Customer> getAllCustomer();
	public boolean update(Customer p);
}
