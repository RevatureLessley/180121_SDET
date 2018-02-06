package com.dao;

import static com.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.adminend.AdminAccount;
import com.userend.BankAccount;
import com.util.Connections;

public class AdminAccountDaoImp implements AdminAccountDao {

	@Override
	public AdminAccount selectAdminAccountByUN(String Password) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		AdminAccount admin = null;
		Connection conn = Connections.getConnection(); 
			
			String sql = "SELECT * FROM AdminAccount WHERE UserName = 'ADMIN' AND PW = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, Password);
			rs = ps.executeQuery();
			
			while(rs.next()) {
			admin = new AdminAccount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			
			close(ps);
			close(rs);
			close(conn);
			
			return admin;
			}
		
		return null;
	}
			


	@Override
	public void ApproveUserAccount(String username) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {
			
			String sql = "UPDATE BankAccount SET AdminApproval = 1 WHERE UserName = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			System.out.println(ps.executeUpdate() + " Account Updated");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}		
	}

}
