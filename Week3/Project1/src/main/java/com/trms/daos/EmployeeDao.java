package com.trms.daos;

import com.trms.beans.Employee;

public interface EmployeeDao {
	public Employee getEmployee(int empId);
	public int getTitle(int empId);
	public int getSubordinates(int empId);
	public int getReportsTo(int empId);
	public Employee getDepartAndTitle(int empId);
}
