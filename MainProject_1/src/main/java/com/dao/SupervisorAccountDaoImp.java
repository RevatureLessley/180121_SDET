package com.dao;

import static com.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.adminend.SupervisorAccount;
import com.util.Connections;

public class SupervisorAccountDaoImp implements SupervisorAccountDao {

	@Override
	public SupervisorAccount getUN_PW(SupervisorAccount visor) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = Connections.getConnection(); 
			
			String sql = "SELECT UserName, PW, SupervisorId, ReferenceId FROM Supervisor WHERE UserName = ? AND PW = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, visor.getUserName());
			ps.setString(2, visor.getPassword());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SupervisorAccount Acc = new SupervisorAccount(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			
			close(ps);
			close(rs);
			close(conn);
			
			return Acc;
			}
		
		return null;
	}	
	

}
