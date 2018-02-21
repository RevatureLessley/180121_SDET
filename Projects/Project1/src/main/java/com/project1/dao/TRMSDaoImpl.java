package com.project1.dao;

import static com.project1.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project1.util.Connections;
import com.project1.beans.Personal;
import com.project1.beans.Account;
import com.project1.beans.Event;
import com.project1.beans.Reimbursement;

public class TRMSDaoImpl implements TRMSDao {
	
	List<Personal> employees;
	List<Account> accounts;
	List<Event> events;
	List<Reimbursement> reimbursements;
	Personal employee;
	Account account;
	Event event;
	Reimbursement reimbursement;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql;
	
//======================= INSERTION METHODS =================================//
	@Override
	public void insertIntoPersonal(String email, String firstName, String lastName, String address, String jobTitle, String date ) {
		try (Connection conn = Connections.getConnection()) {
			sql = "INSERT INTO personal_info VALUES (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, address);
			ps.setString(5, jobTitle);
			ps.setString(6, date);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}
	
	@Override
	public void insertIntoAccounts(String email, String uname, String pw, int isBenCo, int isDirSup, int isDepHead) {
		try (Connection conn = Connections.getConnection()) {
			sql = "INSERT INTO account_info VALUES (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, uname);
			ps.setString(3, pw);
			ps.setInt(4, isBenCo);
			ps.setInt(5, isDirSup);
			ps.setInt(6, isDepHead);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}
	
	@Override
	public void insertIntoEvents(String email, String event, String depHeadEmail, String benCoEmail, String dirSupEmail, 
			String approvalEmail, String justification, String grade, String passFail, String startDate, String endDate) {
		try (Connection conn = Connections.getConnection()) {
			sql = "INSERT INTO events VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, event);
			ps.setString(3, depHeadEmail);
			ps.setString(4, benCoEmail);
			ps.setString(5, dirSupEmail);
			ps.setString(6, approvalEmail);
			ps.setString(7, justification);
			ps.setString(8, grade);
			ps.setString(9, passFail);
			ps.setString(10, startDate);
			ps.setString(11, endDate);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}
	
	@Override
	public void insertIntoReimbursements(String email, double available, double pending, double awarded, double total, String lastReimDate) {
		try (Connection conn = Connections.getConnection()) {
			sql = "INSERT INTO reimbursements VALUES (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setDouble(2, available);
			ps.setDouble(3, pending);
			ps.setDouble(4, awarded);
			ps.setDouble(5, total);
			ps.setString(6, lastReimDate);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}



//======================= RETRIEVAL METHODS =================================//
	
	@Override
	public List<Personal> getAllPersonal() {
		employees = new ArrayList<>();
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM personal_info";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				employees.add(new Personal(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return employees;
	}
	
	@Override
	public List<Account> getAllAccounts() {
		accounts = new ArrayList<>();
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM account_info";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				accounts.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getInt(4), rs.getInt(5), rs.getInt(6)));
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
	public List<Event> getAllEvents() {
		events = new ArrayList<>();
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM events";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				events.add(new Event(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
						rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return events;
	}
	
	@Override
	public List<Reimbursement> getAllReimbursements() {
		reimbursements = new ArrayList<>();
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM reimbursements";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				reimbursements.add(new Reimbursement(rs.getString(1), rs.getDouble(2), rs.getDouble(3), 
						rs.getDouble(4), rs.getDouble(5), rs.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return reimbursements;
	}
/*
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
		logger.info("Accounts retrieved from database.");
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
	}*/
}
