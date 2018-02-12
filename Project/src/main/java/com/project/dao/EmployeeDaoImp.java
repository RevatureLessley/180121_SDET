package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.project.beans.Employee;
import com.project.util.CloseStreams;
import com.project.util.Connections;

public class EmployeeDaoImp implements EmployeeDao {
	
	
	/**
	 * Getting all employee information from database
	 */
	@Override
	public List<Employee> getAllEmployee() {
		
		Statement stmt = null;
		ResultSet rs = null;
		List<Employee> employees = new ArrayList<>();
		
		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM ALLEMPLOYEE";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); 
		
			while (rs.next()) {
				employees.add(new Employee(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseStreams.close(stmt);
			CloseStreams.close(rs);
		}

		return employees;

	}

	/**
	 * Adding an employee to database
	 */
	
	@Override
	public boolean addEmployee(Employee e) {
		
		CallableStatement stmt = null;
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call INSERT_EMPLOYEE(?,?,?,?,?,?,?,?)}");
			stmt.setString(1, e.getFirstName());
			stmt.setString(2, e.getLastName());
			stmt.setString(3, e.getUserName());
			stmt.setString(4, e.getPassword());
			stmt.setString(5, e.getTitle());
			stmt.setString(6, e.getSuper_firstName());
			stmt.setString(7, e.getSuper_lastName());
			stmt.setString(8, e.getEmail());
			stmt.execute();
		}catch(SQLException e1){
			e1.printStackTrace();
			return false;
		}finally{
			CloseStreams.close(stmt);
		}
		return true;
	}

	/**
	 * Delete an employee from database
	 */
	@Override
	public boolean deleteEmployeeByUserName(String u) {
		
		CallableStatement stmt = null;
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call DELETE_EMPLOYEE(?)}");
			stmt.setString(1, u);
			stmt.execute();
		}catch(SQLException e){
			return false;
		}finally{
			CloseStreams.close(stmt);
		}
		return true;
	}

}
