package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revature.beans.Employees;
import com.revature.util.ConnectionUtil;


public class EmployeeDao {
	public Employees loginEmp(String username, String password) {
		//return to servlet if deptHead is true
		//render manager view
		int hashedpass = password.hashCode();
		Employees emp = new Employees();
		PreparedStatement prepstate;
		String sql = "select * from employees where email = ?";
		try(Connection conn = ConnectionUtil.getConnection()){
			
			prepstate = conn.prepareStatement(sql);
			prepstate.setString(1, username);
			ResultSet rs = prepstate.executeQuery();

			while(rs.next()) {
				int pwd = rs.getInt("password");

				if(pwd == hashedpass) {

					int eid = rs.getInt("eid");
					int supervisor = rs.getInt("supervisor");
					int deptHead = rs.getInt("departmentHead");
					String firstname = rs.getString("firstname");
					String lastname = rs.getString("lastname");
					int isDeptHead = rs.getInt("isDeptHead");
					int isBenCo = rs.getInt("isBenCo");
					int isSup = rs.getInt("isSup");
					emp.setEid(eid);
					emp.setSupervisor(supervisor);
					emp.setDeptHead(deptHead);
					emp.setFirstname(firstname);
					emp.setLastname(lastname);
					boolean boolBenCo = (isBenCo != 0);
					boolean boolDeptHead = (isDeptHead != 0);
					boolean boolSup = (isSup != 0);
					emp.setBenCo(boolBenCo);
					emp.setIsDeptHead(boolDeptHead);
					emp.setSup(boolSup);
				}
				else {
					return null;
				}
			}
			rs.close();
			prepstate.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.getMessage();
		}
		return emp;
	}
	public void createEmp(Employees emp) {
		
	}
}