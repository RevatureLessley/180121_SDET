package com.miniproj.dao;

import static com.miniproj.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.miniproj.beans.Account;
import com.miniproj.util.Connections;

public class AccountDaoImpl implements AccountDao {

/*
 * All queries and updates will happen in this class.
 * Accounts cannot be deleted (for practical purposes), 
 * so a closed account will just be deactivated, with no funds in either account.
 */
	List<Account> accounts;
	Account account;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql;

//======================= INSERTION METHODS =================================//
	@Override
	public void insertIntoUserInfo(String email, String fname, String lname) {
		try (Connection conn = Connections.getConnection()) {
			sql = "INSERT INTO user_info VALUES (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}


	@Override
	public void insertIntoAccountInfo(String email, String uname, String pw, int isAd, int isAc, int isCl) {
		try (Connection conn = Connections.getConnection()) {
			sql = "INSERT INTO account_info VALUES (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, uname);
			ps.setString(3, pw);
			ps.setInt(4, isAd);
			ps.setInt(5, isAc);
			ps.setInt(6, isCl);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}
	
	@Override
	public void insertIntoBalanceInfo(String email, double ch, double sa) {
		try (Connection conn = Connections.getConnection()) {
			sql = "INSERT INTO balance_info VALUES (?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setDouble(2, ch);
			ps.setDouble(3, sa);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}

//======================= RETRIEVAL METHODS =================================//
	@Override
	public List<Account> getAllAccounts() {
		accounts = new ArrayList<>();
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM user_info " + 
					"FULL OUTER JOIN account_info " + 
					"ON user_info.u_email = account_info.a_email " + 
					"FULL OUTER JOIN balance_info " + 
					"ON account_info.a_email = balance_info.b_email";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				accounts.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3), 
				rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), 
				rs.getInt(9), rs.getDouble(11), rs.getDouble(12)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		for (Account a : accounts) System.out.println(a);
		return accounts;
	}

	@Override
	public Account selectAccountByEmail(String email) {
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM user_info " + 
					"FULL OUTER JOIN account_info " + 
					"ON user_info.u_email = account_info.a_email " + 
					"FULL OUTER JOIN balance_info " + 
					"ON account_info.a_email = balance_info.b_email " + 
					"WHERE b_email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();

			account = new Account(rs.getString(1), rs.getString(2), rs.getString(3), 
				rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), 
				rs.getInt(9), rs.getDouble(11), rs.getDouble(12));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return account;
	}

	
	
//======================= UPDATE METHODS =================================//
	@Override
	public void updateUserInfo(String email, String col, String newVal) {
		try (Connection conn = Connections.getConnection()) {
			sql = "UPDATE user_info " + 
					"SET ? = ? " + 
					"WHERE u_email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setString(1, col);
			ps.setString(2, newVal);
			ps.setString(3, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
	}
	
	@Override
	public void updateAccountInfo(String email, String col, String newVal) {
		try (Connection conn = Connections.getConnection()) {
			sql = "UPDATE account_info " + 
					"SET ? = ? " + 
					"WHERE u_email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setString(1, col);
			ps.setString(2, newVal);
			ps.setString(3, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
	}
	
	@Override
	public void updateAccountStatusInfo(String email, String col, int newVal) {
		try (Connection conn = Connections.getConnection()) {
			sql = "UPDATE account_info " + 
					"SET ? = ? " + 
					"WHERE u_email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setString(1, col);
			ps.setInt(2, newVal);
			ps.setString(3, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
	}
	
	@Override
	public void updateBalanceInfo(String email, String col, double newVal) {
		try (Connection conn = Connections.getConnection()) {
			sql = "UPDATE balance_info " + 
					"SET ? = ? " + 
					"WHERE u_email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setString(1, col);
			ps.setDouble(2, newVal);
			ps.setString(3, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
	}
	
//	@Override
//	public void deleteAccountByEmail(String email) {
//		return;
//	}
}
