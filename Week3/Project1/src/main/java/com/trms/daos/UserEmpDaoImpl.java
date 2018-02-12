package com.trms.daos;

import static com.trms.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.trms.util.Connections;

public class UserEmpDaoImpl implements UserEmpDao {
	final static Logger logger = Logger.getLogger(UserEmpDaoImpl.class);
	
	public boolean insertUser(int empid, String email, String username, String password) {
		CallableStatement cs = null;
		boolean success = false;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "{call usersemp_insert(?,?,?,?)}";
			cs = conn.prepareCall(sql);
			cs.setInt(1, empid);
			cs.setString(2, username);
			cs.setString(3, password);
			cs.setString(4, email);
			
			success = (cs.executeUpdate() == 1);
			
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(cs);
		}
		
		return success;
	}
	
	public int getUserEmpId(String username, String password) {
		int id = -1;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT useremp_id FROM usersemp WHERE user_name = ? AND user_password = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt(1);
			}
			logger.info("getUserEmpId() : id = " + id);
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return id;
	}
}
