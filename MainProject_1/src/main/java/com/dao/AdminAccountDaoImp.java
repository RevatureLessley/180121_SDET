package com.dao;

import static com.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.adminend.BenCoAccount;
import com.adminend.DeptHeadAccount;
import com.adminend.SupervisorAccount;
import com.util.Connections;

public class AdminAccountDaoImp implements AdminAccountDao{

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

	@Override
	public DeptHeadAccount getUN_PW(DeptHeadAccount dept) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = Connections.getConnection(); 
			
			String sql = "SELECT UserName, PW, Department_Head_Id, DepartmentId FROM DepartmentHead WHERE UserName = ? AND PW = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, dept.getUserName());
			ps.setString(2, dept.getPassword());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				DeptHeadAccount Acc = new DeptHeadAccount(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			
			close(ps);
			close(rs);
			close(conn);
			
			return Acc;
			}
		
		return null;
	}

	@Override
	public BenCoAccount getUN_PW(BenCoAccount benco) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = Connections.getConnection(); 
			
			String sql = "SELECT UserName, PW, BenCo_Id FROM BenCo WHERE UserName = ? AND PW = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, benco.getUserName());
			ps.setString(2, benco.getPassword());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				BenCoAccount Acc = new BenCoAccount(rs.getString(1), rs.getString(2), rs.getInt(3));
			
			close(ps);
			close(rs);
			close(conn);
			
			return Acc;
			}
		
		return null;
	}

	

}
