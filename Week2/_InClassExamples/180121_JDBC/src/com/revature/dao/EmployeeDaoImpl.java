package com.revature.dao;

/*
 * Any method you statically import can be called by itself within the class
 */
import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.util.Connections;

public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public void insertEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
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
		
		/*
		 * Try-With-Resources will close any streams you create within the
		 * parenthesis' of the try block, once the try-catch is finished.
		 */
		try(Connection conn = Connections.getConnection()){
			//First step in using the Statement object, is create a SQL query to 
			//use for the database;
			
			String sql = "SELECT * FROM employees";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql); //Executing queries, brings back resultsets
			
			while(rs.next()){
				emps.add(new Employee(
							rs.getInt("E_ID"),
							rs.getString(2),
							rs.getInt(3),
							rs.getString(4)
						));
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
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
