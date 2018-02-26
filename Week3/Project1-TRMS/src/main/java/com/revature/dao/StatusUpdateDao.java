package com.revature.dao;

import java.util.List;

import com.revature.beans.Status;

public interface StatusUpdateDao {

	public int updateStatus ( int reiid, String afname, String alname, int status, String note);
	public int getStatus (int s,  int initial);
	public List<Status> getReimbursements(int emp_id);
	public List<Status> getAwaitingReimbursements(String role);
}
