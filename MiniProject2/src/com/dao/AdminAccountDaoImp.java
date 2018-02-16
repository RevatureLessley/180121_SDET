package com.dao;

import static com.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.adminend.AdminAccount;
import com.adminend.Transactions;
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
		ResultSet rs = null;
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


	@Override
	public List<Transactions> getAllTransactions() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Transactions> trans = new ArrayList<>();
		
		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM Transactions";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 

			while (rs.next()) {
				trans.add(new Transactions(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),  rs.getInt(5)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return trans;
	}



	@Override
	public List<BankAccount> getAllAccounts() {
		Statement stmt = null;
		ResultSet rs = null;
		List<BankAccount> users = new ArrayList<>();
		
		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM BankAccount";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 

			while (rs.next()) {
				users.add(new BankAccount(rs.getInt(1), rs.getString(2), rs.getString(3),  rs.getString(4), rs.getInt(6)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return users;		
	}

	public void adminApprovalNeeded() {
		PreparedStatement ps = null;
		Statement stmt = null;
		ResultSet rs = null;
		try (Connection conn = Connections.getConnection()) {
			
			
			
				String sql = "SELECT Username FROM BankAccount WHERE AdminApproval = 0";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql); 

				while (rs.next()) {
					System.out.println(rs.getString(1));
				}
				
				System.out.println("\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

	}
}
