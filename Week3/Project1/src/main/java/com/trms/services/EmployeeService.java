package com.trms.services;

import com.trms.beans.Employee;
import com.trms.daos.EmployeeDao;
import com.trms.daos.EmployeeDaoImpl;

public class EmployeeService {
	
	public static Employee getUserEmpId(int empId) {
		EmployeeDao dao = new EmployeeDaoImpl();
		
		return dao.getEmployee(empId);
	}
}
