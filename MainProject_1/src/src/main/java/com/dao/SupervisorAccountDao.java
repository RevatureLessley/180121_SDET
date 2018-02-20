package com.dao;

import java.sql.SQLException;

import com.adminend.SupervisorAccount;

public interface SupervisorAccountDao {

	public SupervisorAccount getUN_PW(SupervisorAccount user) throws SQLException;
}
