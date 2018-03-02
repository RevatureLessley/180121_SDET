package com.trms.services;

import com.trms.beans.Employee;

public class ValidateLogin {

	public static boolean validate(String usernamein, String passwordin) {
		Employee emp = EmployeeService.getEmpByUsername(usernamein);

		if (emp == null)
			return false;
		else if (emp.getUsername().equals(usernamein)
				&& emp.getPassword().equals(passwordin))
			return true;
		else
			return false;
	}
	
	
}
