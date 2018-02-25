package com.revature.debug;

import com.revature.beans.Employee;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbursementDaoImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Driver {

	public static void main(String[] args) {
		
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();

	/*Debugging checkUniqueUsername*/
	/*	Employee test = new Employee(0, "test", "test", "test", "test", "test@testmail.com", "test",
				"test","test","te", 0, "test", "test", "test");
	Employee c = new Employee(8,"christian","diaz","cdiaz8369","Araceli8","cdiaz@dogmail.com","DEC-19-1990","91-52 112th street","queens", "NY",11418,"employee","game design","jasmin");
	boolean unique = empDao.checkUniqueUsername(test);
	boolean check = empDao.checkUniqueUsername(c);
	System.out.println("Is test a unique employee? " + unique);
	System.out.println("Is c a unique employee? " + check);
	System.out.println("===========================================");
 	*/
		
	/*Debugging addEmployee*/
	/*	Employee test = new Employee(0, "test", "test", "test", "test", "test@testmail.com","test" , "test",0, 1000);
	  	empDao.addEmployee(test);
		int count = empDao.totalEmployees();
		System.out.println("Total employees? = " + count);
		System.out.println("===========================================");
	*/
	
	/*Debugging deleteEmployee*/
	/*	Employee test = new Employee(0, "test", "test", "test", "test", "test@testmail.com","test" , "test",0, 1000);
		empDao.deleteEmployee(test);
		int count = empDao.totalEmployees();
		System.out.println("Total employees? = " + count);
		System.out.println("===========================================");
	*/
	
	/*Debugging getAllEmployees()*/
	//System.out.println(empDao.getAllEmployees());
	
	/*Debugging getAllSupervisors()*/
	//System.out.println(empDao.getAllSupervisors());
		
	/*Debugging checkCredentials()*/
/*	int t1 = empDao.checkCredentials("usernameNotFound", "UsernameNotFOund"); // Should return 1, "Username NOT found"
	int t2 = empDao.checkCredentials("cdiaz8369", "Arceli"); // Should return 2, "Wrong Password"
	int t3 = empDao.checkCredentials("cdiaz8369", "Araceli8"); //User is a regular employee;
	int t4 = empDao.checkCredentials("funsizejas", "live4free"); // User is a supervisor
	int t5 = empDao.checkCredentials("liger333", "BrastleTiger423"); // User is a department head
	int t6 = empDao.checkCredentials("clara1953", "Aquamarina1"); // User is benefits coordinator
	
	System.out.println("Should be 1 because user is not in list: " + t1);
	System.out.println("Should be 2 because it is the wrong password: " + t2);
	System.out.println("Should be 3 cause user is a regular employee: " + t3);
	System.out.println("Should be 4 cause user is a supervisor: " + t4);
	System.out.println("Should be 5 cause user is a head: " + t5);
	System.out.println("Should be 6 cause user is a benco: " + t6);
*/
		
	/*Debugging getEmployee()*/
/*	System.out.println(empDao.getEmployee(8)); //Should return Christian Diaz
	System.out.println(empDao.getEmployee("clara1953"));   // Should return Clara Martinez
*/	
		
// =======================================================================================================================================//	
		
	ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		
	/*Debugging checkEmpty() and totalReimbursements() */
/*	System.out.println(reimDao.checkEmpty());
	System.out.println(reimDao.totalReimbursements());
*/
	/*Debugging  addReimbursements and deleteReimbursements*/
	/*	Reimbursement r = new Reimbursement(0, 0, "FirstName", "LastName", "DateOfEvent", "11:22PM",
				"LocationOfEvent", "DescriptionOfEvent", 0, "Grading Format", "TypeOfEvent",
				"WorkRelatedjustification");
		
		reimDao.addReimbursement(r);
		System.out.println(reimDao.checkEmpty());
		reimDao.deleteReimbursement(1);
		System.out.println(reimDao.checkEmpty());
	*/
		
		
	/*Debugging getAllReimbursements()*/
/*	Reimbursement r = new Reimbursement(0, 0, "FirstName", "LastName", "DateOfEvent", "11:22PM",
			"LocationOfEvent", "DescriptionOfEvent", 0, "Grading Format", "TypeOfEvent",
			"WorkRelatedjustification");
	
	reimDao.addReimbursement(r);
	System.out.println(reimDao.getAllReimbursement());
	reimDao.deleteReimbursement(1);
	System.out.println(reimDao.getAllReimbursement());
*/
	//System.out.println(reimDao.getAllAprovedReimbursement());
	
	
	
	
	}

}
