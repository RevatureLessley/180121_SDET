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
 * so a closed account will just be terminated, with no funds in either account.
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
			rs.next();
			
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
	public void updateFirstName(String email, String first) {
		try (Connection conn = Connections.getConnection()) {
				sql = "UPDATE user_info " + 
						"SET u_first_name = ? " + 
						"WHERE u_email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setString(1, first);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
	}
	
	@Override
	public void updateLastName(String email, String last) {
		try (Connection conn = Connections.getConnection()) {
				sql = "UPDATE user_info " + 
						"SET u_last_name = ? " + 
						"WHERE u_email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setString(1, last);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
	}
	
	@Override
	public void updatePassword(String email, String pass) {
		try (Connection conn = Connections.getConnection()) {
				sql = "UPDATE account_info " + 
						"SET a_password = ? " + 
						"WHERE a_email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setString(1, pass);
			ps.setString(2, email);
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
			if (col == "a_is_admin")
				sql = "UPDATE account_info " + 
						"SET a_is_admin = ? " + 
						"WHERE a_email = ?"; 
			else if (col == "a_is_active")
				sql = "UPDATE account_info " + 
						"SET a_is_active = ? " + 
						"WHERE a_email = ?"; 
			else
				sql = "UPDATE account_info " + 
						"SET a_is_closed = ? " + 
						"WHERE a_email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setInt(1, newVal);
			ps.setString(2, email);
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
			if (col == "b_checkings")
				sql = "UPDATE balance_info " + 
						"SET b_checkings = ? " + 
						"WHERE b_email = ?"; 
			else
				sql = "UPDATE balance_info " + 
						"SET b_savings = ? " + 
						"WHERE b_email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, newVal);
			ps.setString(2, email);
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
