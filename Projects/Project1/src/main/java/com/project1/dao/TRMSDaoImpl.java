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
	List<String> emails;
	Personal employee;
	Account account;
	Event event;
	Reimbursement reimbursement;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String sql;
	
//======================= INSERTION METHODS =================================//
	@Override
	public void insertIntoPersonal(String email, String firstName, String lastName, String address, String date ) {
		try (Connection conn = Connections.getConnection()) {
			sql = "INSERT INTO personal_info VALUES (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, address);
			ps.setString(5, date);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}
	
	@Override
	public void insertIntoAccounts(String email, String pw, String isBenCo, String isDirSup, String isDepHead) {
		try (Connection conn = Connections.getConnection()) {
			sql = "INSERT INTO account_info VALUES (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pw);
			ps.setString(3, isBenCo);
			ps.setString(4, isDirSup);
			ps.setString(5, isDepHead);
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



//======================= RETRIEVAL METHODS (ALL) =================================//
	
	@Override
	public List<Personal> getAllPersonal() {
		employees = new ArrayList<>();
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM personal_info";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				employees.add(new Personal(rs.getString(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5)));
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
						rs.getString(4), rs.getString(5), rs.getString(6)));
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
	
	@Override
	public List<String> getAllEmails() {
		emails = new ArrayList<>();
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT email FROM personal_info";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			while (rs.next()) {
				emails.add(rs.getString(1));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return emails;
	}
	
//======================= RETRIEVAL METHODS (SINGLE) =================================//
	
	@Override
	public Personal selectPersonalByEmail(String email) {
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM personal_info WHERE email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			rs.next();
			
			employee = new Personal(rs.getString(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return employee;
	}
	
	@Override
	public Account selectAccountByEmail(String email) {
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM account_info WHERE email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			rs.next();
			
			account = new Account(rs.getString(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return account;
	}
	
	@Override
	public Event selectEventByEmail(String email) {
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM events WHERE email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			rs.next();
			
			event = new Event(rs.getString(1), rs.getString(2), rs.getString(3), 
					rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
					rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return event;
	}
	
	@Override
	public Reimbursement selectReimbursementByEmail(String email) {
		try (Connection conn = Connections.getConnection()) {
			sql = "SELECT * FROM reimbursements WHERE email = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			rs.next();
			
			reimbursement = new Reimbursement(rs.getString(1), rs.getDouble(2), rs.getDouble(3), 
					rs.getDouble(4), rs.getDouble(5), rs.getString(6));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return reimbursement;
	}


	
//======================= UPDATE METHODS (BY TYPE) =================================//

	@Override
	public void updateStringColumn(String email, String table, String column, String value) {
		try (Connection conn = Connections.getConnection()) {
				sql = "UPDATE " + table + 
						" SET " + column + " = ? " + 
						"WHERE email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setString(1, value);
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
	public void updateDoubleColumn(String email, String table, String column, double value) {
		try (Connection conn = Connections.getConnection()) {
				sql = "UPDATE " + table + 
						" SET " + column + " = ? " + 
						"WHERE email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, value);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
	}
	
//======================= SELECT COLUMN VALUE METHODS (BY TYPE) =================================//
	@Override
	public String getStringValue(String email, String table, String column) {
		String value = null;
		try (Connection conn = Connections.getConnection()) {
				sql = "SELECT " + column + " FROM " + table 
						+ " WHERE email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			rs.next();
			value = rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return value;
	}
	
	@Override
	public double getDoubleValue(String email, String table, String column) {
		double value = 0.0;
		try (Connection conn = Connections.getConnection()) {
				sql = "SELECT " + column + " FROM " + table 
						+ " WHERE email = ?"; 
			ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			rs = ps.executeQuery();
			rs.next();
			value = rs.getDouble(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return value;
	}
}
