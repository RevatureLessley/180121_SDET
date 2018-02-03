package com.bank.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.beans.User;
import com.bank.util.CloseStreams;
import com.bank.util.Connections;

public class UserDaoImp implements UserDao {

	@Override
	public boolean insertUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAllUser() {
		
		Statement stmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();
		
		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT a.USERNAME, a.PASSWORD, b.STATUS \r\n" + 
					"FROM LOGIN a NATURAL INNER JOIN STATUS b \r\n";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
		
			while (rs.next()) {
				users.add(new User(rs.getString(1), rs.getString(2), rs.getString(3)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(stmt);
			CloseStreams.close(rs);
		}

		return users;

	}

	@Override
	public boolean deleteUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User u) {
		// TODO Auto-generated method stub
		return false;
	}

}
