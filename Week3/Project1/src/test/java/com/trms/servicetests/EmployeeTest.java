package com.trms.servicetests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.trms.beans.Employee;
import com.trms.services.EmployeeService;

public class EmployeeTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void IdFromEmployeeObj() {
		assertEquals(6, EmployeeService.getEmpByUserEmpId(6).getId());
	}
	
	@Test
	public void numberOfSubordinates() {
		assertEquals(2, EmployeeService.getSubordinates(1));
	}
	
	@Test
	public void numberOfSubordinates01() {
		assertEquals(0, EmployeeService.getSubordinates(7));
	}
	
	@Test
	public void reportsToValue() {
		assertEquals(1, EmployeeService.getReportsTo(2));
	}
	
	@Test
	public void reportsToValue01() {
		assertEquals(0, EmployeeService.getReportsTo(1));
	}
	
	@Test
	public void departmentIdValue() {
		Employee e = EmployeeService.getDepartTitle(3);
		assertEquals(2, e.getDepartmentId());
	}
	
	@Test
	public void titleIdValue() {
		Employee e = EmployeeService.getDepartTitle(3);
		assertEquals(200, e.getTitleId());	
	}
	
	@Test
	public void departmentHeadEmpId() {
		assertEquals(5, EmployeeService.getDepartmentHead(7));
	}

}
