package com.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.beans.Customer;
import com.bank.util.CloseStreams;
import com.bank.util.Connections;

public class CustomerDaoImp implements CustomerDao {

	@Override
	public List<Customer> getAllCustomer() {
		// get all existing user
		Statement stmt = null;
		ResultSet rs = null;
		List<Customer> customers = new ArrayList<>();
		
		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT a.USERNAME, a.PASSWORD, b.STATUS, c.AMOUNT \r\n" + 
					"FROM LOGIN a NATURAL INNER JOIN STATUS b \r\n" + 
					"NATURAL INNER JOIN ACCOUNT c";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
		
			while (rs.next()) {
				customers.add(new Customer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(stmt);
			CloseStreams.close(rs);
		}

		return customers;

	}

	
	@Override
	public boolean update(Customer p) {
		// Add or reduce amount in customer account
		CallableStatement stmt = null;
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call updateCustomer(?,?)}");
			stmt.setString(1, p.getUserName());
			stmt.setDouble(2, p.getAmount());
			stmt.execute();
		}catch(SQLException e){
			return false;
		}finally{
			CloseStreams.close(stmt);
		}
		return true;
	}

}
