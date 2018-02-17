package com.revature.service;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

import com.revature.beans.Reimbursement;
import com.revature.beans.RejectedReimbursement;
import com.revature.beans.AprovedReimbursement;
import com.revature.beans.Employee;

import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.ReimbursementDaoImpl;

import com.revature.util.Bridge;
import static com.revature.util.CloseStreams.close;
/**
 * @author Christian Diaz
 *
 */
/**DataService is a class that used to to communicate between the RegisterEmp servlet and the EmployeeDaoImpl**/
public class DataService {
	/**This is the main function used by DataService; insertEmployee will take several arguments (one for each variable in the employee class)
	 * and the create an instance of the EmployeeDaoImpl called empDao. This will grant DataService access to the addEmployee function.
	 * An employee object will also be created with all the arguments passed, and this new object will be passed to the function addEmployees of empDao.**/
	public static void insertEmployee(int emp_id, String fname, String lname, 
			String username, String password, String email, String role,
			String department, int sup_id, int amount) {
		
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		Employee emp = new Employee (emp_id, fname, lname, username, password, email, role, department, sup_id, amount);
		empDao.addEmployee(emp);
	}
	/**This function is used to check if given a provided string, the string matches a username in the database. This is done using the 
	 * 	checkUniqueUsername() method of empDao; an instance of EmployeeDaoImpl. **/
	public static boolean dupName(String name) {
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		return empDao.checkUniqueUsername(name);
	}
}
