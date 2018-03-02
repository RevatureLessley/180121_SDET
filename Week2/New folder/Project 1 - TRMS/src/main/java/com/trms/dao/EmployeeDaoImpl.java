package com.trms.dao;

import static com.trms.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.trms.beans.Employee;
import com.trms.util.Connections;

public class EmployeeDaoImpl{

	public int insertEmployee(Employee emp) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "INSERT INTO employees (emp_id, firstname, lastname, username, password, email, title) VALUES(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			long id = getMaxEmpId();
			ps.setLong(1, id + 1);
			ps.setString(2, emp.getFname());
			ps.setString(3, emp.getLname());
			ps.setString(4, emp.getUsername());
			ps.setString(5, emp.getPassword());
			ps.setString(6, emp.getEmail());
			ps.setString(7, emp.getTitle());
			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return 0;
	}

	public Employee getEmployeeById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;

		try (Connection conn = Connections.getConnection()) {

			// String sql = "SELECT * FROM employees WHERE id = ?";
			String sql = "SELECT * FROM EMPLOYEEVIEW WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new Employee(rs.getLong("emp_id"),
						rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"),
						rs.getString("title"), rs.getString("address"),
						rs.getString("city"), rs.getString("state"),
						rs.getString("tel"), rs.getString("email"),
						rs.getString("supervisor"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return emp;
	}

	public List<Employee> getAllEmployee() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> emps = new ArrayList<>();

		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM EMPLOYEEVIEW";

			// String sql = "SELECT * FROM employees";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				emps.add(new Employee(rs.getLong(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5), rs
						.getString("title"), rs.getString("address"), rs
						.getString("city"), rs.getString("state"), rs
						.getString("tel"), rs.getString("email"), rs
						.getString("supervisor")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return emps;
	}

	public Employee getEmployeeByUsername(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;

		try (Connection conn = Connections.getConnection()) {

			// String sql = "SELECT * FROM employees WHERE username = ?";
			String sql = "SELECT * FROM EMPLOYEEVIEW WHERE username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new Employee(rs.getLong("emp_id"),
						rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"),
						rs.getString("title"), rs.getString("address"),
						rs.getString("city"), rs.getString("state"),
						rs.getString("tel"), rs.getString("email"),
						rs.getString("supervisor"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return emp;
	}

	public boolean updateEmployeeByUsername(String username, Employee emp) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "UPDATE employees "
					+ "SET firstname = ?, lastname = ?, email = ?, address = ?, city = ?, state = ?, tel = ?"
					+ "WHERE username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, emp.getFname());
			ps.setString(2, emp.getLname());
			ps.setString(3, emp.getEmail());
			ps.setString(4, emp.getAddress());
			ps.setString(5, emp.getCity());
			ps.setString(6, emp.getState());
			ps.setString(7, emp.getTel());
			ps.setString(8, username);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return false;
	}

	public boolean updateTitleById(long id, String title) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "UPDATE employees " + "SET title = ? WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setLong(2, id);
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return false;
	}

	public long getMaxEmpId() {
		Statement stmt = null;
		ResultSet rs = null;
		int max = -1;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT Max(emp_id) FROM employees";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				max = rs.getInt("MAX(emp_id)");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Get Max emp_id Failed.");
		} finally {
			close(stmt);
			close(rs);
		}

		return max;
	}

	public boolean updateSupervisorById(long id, String supervisorId) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			if (!supervisorId.equals("")) {
				String sql = "UPDATE employees "
						+ "SET supervisor_id = ? WHERE emp_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setLong(1, Long.parseLong(supervisorId));
				ps.setLong(2, id);
			}else{
				String sql = "UPDATE employees "
						+ "SET supervisor_id = NULL WHERE emp_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setLong(1, id);
			}
			ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return false;
	}

}
