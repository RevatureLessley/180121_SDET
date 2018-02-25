package com.dao;

import java.sql.SQLException;

import com.adminend.BenCoAccount;
import com.adminend.DeptHeadAccount;
import com.adminend.SupervisorAccount;

public interface AdminAccountDao  {

	public SupervisorAccount getUN_PW(SupervisorAccount user) throws SQLException;
	
	public DeptHeadAccount getUN_PW(DeptHeadAccount user) throws SQLException;

	public BenCoAccount getUN_PW(BenCoAccount user) throws SQLException;

	
}
