package com.miniproject.dao;

import static com.miniproject.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.miniproject.users.Admin;
import com.miniproject.users.User;
import com.miniproject.util.Connections;

public class AdminDaoImpl implements AdminDao {

	@Override
	public void insertAdmin(Admin a) {
		CallableStatement stmt = null;
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insert_admin(?,?)}");
			stmt.setString(1, a.getUsername());
			stmt.setString(2, a.getPassword());
			stmt.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		
	}

	@Override
	public Admin getAdmin(String inUsername, String inPassword) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Admin a = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT username FROM admins WHERE username = ? AND p_word = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, inUsername);
			ps.setString(2, inPassword);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				a = new Admin(rs.getString(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return a;
	}
	
}
