package com.miniproject.dao;

import static com.miniproject.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.miniproject.users.Admin;
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
	
}
