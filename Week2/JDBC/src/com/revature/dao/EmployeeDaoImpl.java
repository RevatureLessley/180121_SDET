package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.util.Connections;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public void insertEmployee(Employee emp) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;
		try(Connection conn = Connections.getConnection()) {
			String sql = "INSERT INTO employees VALUES(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, emp.getEmpId());
			ps.setString(2, emp.getEmpName());
			ps.setInt(3, emp.getEmpSalary());
			ps.setString(4, emp.getTitle());
			System.out.println(ps.executeUpdate() + " RECORD(S) INSERTED!");
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}

	@Override
	public Employee selectEmployeeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployee() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> emps = new ArrayList<>();
		
		//try with resources
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT * FROM employees";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				emps.add(new Employee(
							rs.getInt("E_ID"),
							rs.getString(2),
							rs.getInt(3),
							rs.getString(4)
						));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return emps;
	}

	@Override
	public int deleteEmployeeById() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateEmployeeById(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}

}
