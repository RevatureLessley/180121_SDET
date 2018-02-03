package com.miniproject.dao;

import static com.miniproject.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.miniproject.users.User;
import com.miniproject.util.Connections;

public class UserDaoImpl implements UserDao {

	@Override
	public void insertUser(User u) {
		CallableStatement stmt = null;
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insert_into_users(?,?)}");
			stmt.setString(1, u.getUsername());
			stmt.setString(2, u.getPassword());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
	}

	@Override
	public List<User> getAllUsersForAdmin() {
		Statement stmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT username, account_approved, banned FROM users";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				users.add(new User(rs.getString(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}
		
		return users;
	}

}
