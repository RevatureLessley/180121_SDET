package com.banksystem.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.banksystem.beans.Account;
import com.banksystem.util.Connections;

public class AccountDaoImpl implements AccountDao{

	@Override
	public void insertAccount(Account acc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account selectAccountById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccounts() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Account> accs = new ArrayList<>();;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM accounts";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()){
				accs.add(new Account(
						
						));
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Failure");
		}finally{
			
		}
		
		
		return accs;
	}

}
