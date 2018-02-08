package com.revature.dao;

/*
 * Any method you statically import can be called by itself within the class
 */
import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import com.revature.beans.Employee;
import com.revature.util.Connections;

public class EmployeeDaoImpl {

	
	public void insertEmployee(Employee emp) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {
			// First step in using the Statement object, is create a SQL query
			// to
			// use for the database;

			String sql = "INSERT INTO employees (E_NAME, E_SALARY, E_TITLE) VALUES(?,?,?)";
			ps = conn.prepareStatement(sql);
			System.out.println(emp);
			ps.setString(1, emp.getEmpName());
			ps.setInt(2, emp.getEmpSalary());
			ps.setString(3, emp.getTitle());

			System.out.println(ps.executeUpdate() + " RECORD(S) INSERTED!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}

	}

	
	public Employee selectEmployeeById(String id) {
		return null;
	}

	
	public List<Employee> getAllEmployee() {
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> emps = new ArrayList<>();

		/*
		 * Try-With-Resources will close any streams you create within the
		 * parenthesis' of the try block, once the try-catch is finished.
		 */
		try (Connection conn = Connections.getConnection()) {
			// First step in using the Statement object, is create a SQL query
			// to
			// use for the database;

			String sql = "SELECT * FROM employees";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); // Executing queries, brings back
											// resultsets

			while (rs.next()) {
				emps.add(new Employee(rs.getInt("E_ID"), rs.getString(2), rs.getInt(3), rs.getString(4)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return emps;
	}

	
	public int deleteEmployeeById() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateEmployeeById(Employee emp) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void insertEmployeeViaSP(Employee emp){
		CallableStatement stmt = null;
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insertIntoEmployee(?,?,?)}");
			stmt.setString(1, emp.getEmpName());
			stmt.setInt(2, emp.getEmpSalary());
			stmt.setString(3, emp.getTitle());
			stmt.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
	}

}
