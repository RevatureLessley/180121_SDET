package com.revature.dao;

public interface StatusUpdateDao {

	public int updateStatus ( int reiid, String afname, String alname, int status, String note);
	public int getStatus (int s,  int initial);

}
