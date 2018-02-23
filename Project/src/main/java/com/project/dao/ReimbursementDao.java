package com.project.dao;

import java.util.List;

import com.project.beans.Reimbursement;

public interface ReimbursementDao {
	
	public List<Reimbursement> getAllReimbursement();
	public boolean UpdateReimbursement(Reimbursement r);
	
}
