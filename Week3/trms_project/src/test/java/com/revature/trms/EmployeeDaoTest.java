package com.revature.trms;

import org.junit.Test;
import static org.junit.Assert.*;
import com.revature.beans.Employees;
import com.revature.dao.EmployeeDao;

public class EmployeeDaoTest {
	EmployeeDao dao = new EmployeeDao();
	Employees emp = new Employees();
	Employees emptest = new Employees();
	@Test
	public void EmpLoginTest() {
		emp.setFirstname("steven");
		emp.setLastname("c");
		String username = "steven@gmail.com";
		String password = "p4ssw0rd";
		emptest = dao.loginEmp(username, password);
		assertEquals(emp.getFirstname(), emptest.getFirstname());
		assertEquals(emp.getLastname(), emptest.getLastname());
	}

}