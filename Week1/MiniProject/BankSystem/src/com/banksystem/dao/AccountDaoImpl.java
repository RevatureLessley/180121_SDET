package com.banksystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.banksystem.util.CloseStream.close;

import com.banksystem.beans.Account;
import com.banksystem.util.BankLogger;
import com.banksystem.util.Connections;

public class AccountDaoImpl implements AccountDao {

	@Override
	public List<Account> getAllAccounts() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Account> accs = new ArrayList<>();

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT * FROM accounts";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				accs.add(new Account(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getInt(4), rs.getInt(5), rs
						.getDouble(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BankLogger.errorMsg("Get All Accounts Failed.");
		} finally {
			close(stmt);
			close(rs);
		}

		return accs;
	}

	public void getAllAccountsView() {
		Statement stmt = null;
		ResultSet rs = null;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT * FROM All_Accounts";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.printf("%-10s%-20s%-20s%-10s%-20s%-10s\n", "UserId",
					"Username", "Password", "Role", "Status", "Balance");
			while (rs.next()) {
				System.out.printf("%-10s%-20s%-20s%-10s%-20s%-10s\n",
						rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BankLogger.errorMsg("Get All Account View Failed.");
		} finally {
			close(stmt);
			close(rs);
		}

	}

	public int getMaxUserId() {
		Statement stmt = null;
		ResultSet rs = null;
		int max = -1;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT Max(userId) FROM accounts";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				max = rs.getInt("MAX(USERID)");
			}
		} catch (Exception e) {
			e.printStackTrace();
			BankLogger.errorMsg("Get Max UserId Failed.");
		} finally {
			close(stmt);
			close(rs);
		}

		return max;
	}

	@Override
	public void addAccount(Account acc) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {
			
			String sql = "INSERT INTO accounts VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
//			System.out.println(acc);
			ps.setInt(1, acc.getUserid());
			ps.setString(2, acc.getUsername());
			ps.setString(3, acc.getPassword());
			ps.setInt(4, acc.getRole());
			ps.setInt(5, acc.getStatus());
			ps.setDouble(6, acc.getBalance());
			

			BankLogger.infoMsg(ps.executeUpdate() + " RECORD(S) INSERTED!");

		} catch (SQLException e) {
			e.printStackTrace();
			BankLogger.errorMsg("Add Account Failed.");
		} finally {
			close(ps);
		}

	}
	
	public List<Account> getPendingApproveAccount(){
		Statement stmt = null;
		ResultSet rs = null;
		List<Account> accs = new ArrayList<>();

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE acc_status = 2";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				accs.add(new Account(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getInt(4), rs.getInt(5), rs
						.getDouble(6)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BankLogger.errorMsg("Get Pending Accounts Failed.");
		} finally {
			close(stmt);
			close(rs);
		}

		return accs;
		
	}

	public void approveAccount(int index) {
		Statement stmt = null;

		try (Connection conn = Connections.getConnection()) {
			String sql = "UPDATE accounts SET acc_status = 1 WHERE userid = " + index;
			stmt = conn.createStatement();
			BankLogger.infoMsg(stmt.executeUpdate(sql)+ " RECORD(S) UPDATED.");

		} catch (Exception e) {
			e.printStackTrace();
			BankLogger.errorMsg("Approve Account Failed.");
		} finally {
			close(stmt);
		}
	}

	@Override
	public Account getAccountById(int id) {
		Statement stmt = null;
		ResultSet rs = null;
		Account acc = null;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT * FROM accounts WHERE userid = " + id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				acc = new Account(rs.getInt(1), rs.getString(2), rs
						.getString(3), rs.getInt(4), rs.getInt(5), rs
						.getDouble(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BankLogger.errorMsg("Get Account Failed.");
		} finally {
			close(stmt);
			close(rs);
		}

		return acc;
	}

	public void updateBalance(int id, double amount){
		Statement stmt = null;

		try (Connection conn = Connections.getConnection()) {
			String sql = "UPDATE accounts SET balance = " + amount + " WHERE userid = " + id;
			stmt = conn.createStatement();
			BankLogger.infoMsg(stmt.executeUpdate(sql)+ " RECORD(S) UPDATED.");

		} catch (Exception e) {
			e.printStackTrace();
			BankLogger.errorMsg("Update Balance Failed.");
		} finally {
			close(stmt);
		}
	}

	public double getBalanceById(int userid) {
		Statement stmt = null;
		ResultSet rs = null;
		double bal = 0;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT balance FROM accounts WHERE userid = " + userid;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				bal = rs.getDouble("balance");
			}
		} catch (Exception e) {
			e.printStackTrace();
			BankLogger.errorMsg("Get Balance Failed.");
		} finally {
			close(stmt);
			close(rs);
		}

		return bal;
	}
	
}
