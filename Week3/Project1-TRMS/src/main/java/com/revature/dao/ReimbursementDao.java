package com.revature.dao;

import java.util.List;

import com.revature.beans.Reimbursement;


public interface ReimbursementDao {

	public boolean checkEmpty();
	
	public int totalReimbursements();

	public void addReimbursement(Reimbursement reim);
	public void deleteReimbursement(int id);
	public void deleteReimbursement(Reimbursement reim);
	public void aproveReimbursement(int id, int role);
	public void rejectReimbursement(int id);
	
	public Reimbursement getReimbursement(String username);
	public Reimbursement getReimbursement(int id);
	
	public List<Reimbursement> getAllReimbursement (); 
	public List<Reimbursement> getAllReimbursementUser(int emp_id);


	
}
