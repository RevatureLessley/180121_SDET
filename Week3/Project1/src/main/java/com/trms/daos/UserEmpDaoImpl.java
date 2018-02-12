package com.trms.daos;

import static com.trms.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.trms.util.Connections;

public class UserEmpDaoImpl implements UserEmpDao {
	final static Logger logger = Logger.getLogger(UserEmpDaoImpl.class);
	
	public boolean insertUser(int empid, String email, String username, String password) {
		CallableStatement cs = null;
		boolean success = false;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "INSERT INTO usersemp (useremp_id, user_name, user_password, user_email) "
					+ "VALUES (?, ?, ?, ?)";
			sql = "{call usersemp_insert(?,?,?,?)}";
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
}
