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

public class EmployeeDaoImpl implements EmployeeDao {

	public int insertEmployee(Employee emp) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "INSERT INTO employees (emp_id, firstname, lastname, username, password, email) VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			long id = getMaxEmpId();
			ps.setLong(1, id + 1);
			ps.setString(2, emp.getFname());
			ps.setString(3, emp.getLname());
			ps.setString(4, emp.getUsername());
			ps.setString(5, emp.getPassword());
			ps.setString(6, emp.getEmail());
			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return 0;
	}

	public Employee getEmployeeById(String id) {
		return null;
	}

	public List<Employee> getAllEmployee() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> emps = new ArrayList<>();

		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM employees";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				emps.add(new Employee(rs.getLong(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5), rs
						.getString(6), rs.getString(7), rs.getString(8), rs
						.getString(9), rs.getString(10), rs.getString(11)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return emps;
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM employees WHERE username = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				emp = new Employee();
				emp.setUsername(rs.getString("username"));
				emp.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return emp;
	}

	public int updateEmployeeById(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
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

}
