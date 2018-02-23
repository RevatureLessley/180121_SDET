package com.revature.dao;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;


public interface ReimbursementDao {

	public boolean checkEmpty();
	public int totalReimbursements();
	public boolean checkReimbursement(int reid);
	public int getCost(int emp_id);

	public void addReimbursement(Reimbursement reim);
	public int editReimbursement(Reimbursement reim);
	public void deleteReimbursement(int id);
	public void deleteReimbursement(Reimbursement reim);
	public int getStatus(int rei_id);
	public void updateStatus(int id, int s);
	
	public Reimbursement getReimbursement(String username);
	public Reimbursement getReimbursement(int id);
	public Employee getEmployeeByRid (int reid);
	
	
	public List<Reimbursement> getAllReimbursement (); 
	public List<Reimbursement> getAllReimbursementUser(int emp_id);
	public List<Reimbursement> getSuperReimbursementUser(int emp_id);
	public List<Reimbursement> getDepartmentReimbursementUser(String department);

	
}
