package com.revature.service;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;

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
	
	/**InsertEmployee will take several arguments (one for each variable in the employee class)
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
	/**valLogin is used to validate an attempt at logging into TRMS. This method will pass a username and password to empDao's checkCredentials,
	 * This method will return an int; this int will correspond to a code that will indicate the status of the attempted login
	 * Below is the break down for this code:
	 * 0 ==== There are currently no users in TRMS
	 * 1 ==== Username was NOT found in TRMS
	 * 2 ==== Password is INCORRECT for provided username
	 * 3 ==== User is of the regular employee and should taken to a regualr employee page for that user.
	 * 4 ==== User is a Supervisor employee and should be taken to a supervisor login page fir that user.
	 * 5 ==== User is a department head employee and should be taken to a head employee login page for that user.
	 * 6 ==== User is a benefits coordinator employee and should be taken to a benefits coordinate login page for that user.**/
	public static int valLogin(String username, String password) {
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		return empDao.checkCredentials(username, password);
	}

	/**This method will return an Employee object, using a username to identify the employee**/
	public static Employee returnEmployee (String username) {
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		return empDao.getEmployee(username);
	}
	
	
	
	public static void insertNewReimbursement(int empid, String fname, String lname, String dateof, String time, String location,
						String desc, int cost, String gradingFormat, String typeOfEvent, String work) {
		
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		
		int rei_id = reimDao.totalReimbursements();
		
		int approval_state = 0;
		String attachment = "";
		String note = "";
		
		Reimbursement reim = new Reimbursement(rei_id, empid, fname, lname, dateof, time, location, desc, cost, gradingFormat, typeOfEvent, work, approval_state, attachment, note);
		
		reimDao.addReimbursement(reim);
	}
	
	public static int editReimbursement(int rei_id, int empid, String fname, String lname, String dateof, String timeof, String location,
			String desc, int cost, String gradingFormat, String typeofevent, String work) {
	
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		Reimbursement reim = new Reimbursement(rei_id, empid, fname, lname, dateof, timeof, location,
				desc, cost, gradingFormat, typeofevent, work);
		return reimDao.editReimbursement(reim);
	
	}
	
	public static List<Reimbursement> userReimbursements(int emp_id){
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		return reimDao.getAllReimbursementUser(emp_id);
		
	};
	
	public static int getCostAmount(int emp_id) {
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		return reimDao.getCost(emp_id);
	}
	
}
