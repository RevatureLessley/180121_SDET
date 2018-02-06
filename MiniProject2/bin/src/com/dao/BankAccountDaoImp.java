package com.dao;

import static com.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.Connections;
import com.userend.BankAccount;

public class BankAccountDaoImp implements BankAccountDao{
	
	@Override
	public void insertBankAccount(BankAccount user) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "INSERT INTO BankAccount (FirstName, LastName, UserName, PW, Balance, AdminApproval) " + 
						 "VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getBalance());
			ps.setInt(6, user.getAdminApproval());
			
			System.out.println(ps.executeUpdate() + "YOUR ACCOUNT HAS BEEN CREATED, WAITING FOR ADMIN APPROVAL");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
	}


	@Override
	public BankAccount selectBankAccountByUN(String username, String Password) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		BankAccount user = null;
		Connection conn = Connections.getConnection(); 
			
			String sql = "SELECT * FROM BankAccount WHERE UserName = ? AND PW = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, Password);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
			user = new BankAccount(rs.getString(2), rs.getString(3), rs.getString(4), 
								   rs.getString(5), rs.getInt(6), rs.getInt(7));
			
			close(ps);
			close(rs);
			close(conn);
			
			return user;
			
			}
			
		return null;
					
	}

public int getUserID(BankAccount user) throws SQLException {
	PreparedStatement ps = null;
	ResultSet rs = null;
	int Account_ID = 0;

	Connection conn = Connections.getConnection();

		String sql = "SELECT Account_ID FROM BankAccount WHERE UserName = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, user.getUserName());
		rs = ps.executeQuery();
		
		while (rs.next()) {
		Account_ID = rs.getInt(1);
		
		
		close(ps);
		close(rs);
		close(conn);
		return Account_ID;
		}
	
		return 0;
	}

	@Override
	public int getAccountBalance(BankAccount user) throws SQLException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int Balance = 0;

		Connection conn = Connections.getConnection();
	
			String sql = "SELECT Balance FROM BankAccount WHERE UserName = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			rs = ps.executeQuery();
			
			while (rs.next()) {
			Balance = rs.getInt(1);
			
			
			close(ps);
			close(rs);
			close(conn);
			return Balance;
			}
		
			return 0;
	}


	@Override
	public void withdrawFromAccount(BankAccount user, int withdrawAmt) {

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;

		try (Connection conn = Connections.getConnection()) {
			
			user.Withdraw(withdrawAmt);
			String sql = "UPDATE BankAccount SET Balance = ? WHERE UserName = ? ";
			ps = conn.prepareStatement(sql);
			System.out.println(user);
			ps.setInt(1, user.getBalance());
			ps.setString(2, user.getUserName());

			System.out.println(ps.executeUpdate() + " YOUR TRANSACTION IS COMPLETE!");
			
			String sql1 ="INSERT INTO Transactions (Account_ID, UserName, Transaction_Type, Amount) " + 
					 "VALUES(?,?,?,?)";
			ps1 = conn.prepareStatement(sql1);
			ps1.setInt(1, getUserID(user));
			ps1.setString(2, user.getUserName());
			ps1.setString(3, "WithDraw");
			ps1.setInt(4, withdrawAmt);
			
			System.out.println(ps1.executeUpdate() + " TRANSACTION RECORED!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		
	}


	@Override
	public void depositToAccount(BankAccount user, int depositAmt) {

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;

		try (Connection conn = Connections.getConnection()) {
			
			user.Deposit(depositAmt);
			String sql = "UPDATE BankAccount SET Balance = ? WHERE UserName = ? ";
			ps = conn.prepareStatement(sql);
			System.out.println(user);
			ps.setInt(1, user.getBalance());
			ps.setString(2, user.getUserName());
			System.out.println(ps.executeUpdate() + " YOUR TRANSACTION IS COMPLETE!");
			
			String sql1 ="INSERT INTO Transactions (Account_ID, UserName, Transaction_Type, Amount) " + 
					 "VALUES(?,?,?,?)";
			ps1 = conn.prepareStatement(sql1);
			ps1.setInt(1, getUserID(user));
			ps1.setString(2, user.getUserName());
			ps1.setString(3, "Deposit");
			ps1.setInt(4, depositAmt);
			
			System.out.println(ps1.executeUpdate() + " TRANSACTION RECORED!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}

	


	

	

}
