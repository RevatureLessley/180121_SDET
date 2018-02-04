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
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql;
	
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

	@Override
	public Account selectAccountByEmail(String email) {
		return null;
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> accounts = new ArrayList<>();
//		try (Connection conn = Connections.getConnection()) {
//			sql = "SELECT * FROM accounts";
//			ps = conn.prepareStatement(sql);
//			rs = stmt.executeQuery(sql); // Executing queries, brings back ResultSet object
//
//			while (rs.next()) {	//starts at 0
//				accounts.add(new Account(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4)));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(stmt);
//			close(rs);
//		}
//		for (Account a : accounts) System.out.println(a);
		return accounts;
	}

	@Override
	public void deleteAccountByEmail(String email) {
		
		return;
	}

	@Override
	public void updateAccountByEmail(String email) {
		
		return;
	}
}
