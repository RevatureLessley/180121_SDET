package com.revature.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.revature.beans.Attachments;
import com.revature.beans.CustomGrade;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.dao.AttachmentDaoImpl;
import com.revature.dao.CustomGradeDaoImpl;
import com.revature.dao.EmployeeDaoImpl;
import com.revature.dao.GradeDaoImpl;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.dao.StatusUpdateDaoImpl;
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
	
	
	
	public static int insertNewReimbursement(int empid, String fname, String lname, String dateof, String time, String location,
						String desc, int cost, String gradingFormat, String typeOfEvent, String work, int attachment_bit) {
		
		checkNewYear();
		
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		
		int rei_id = reimDao.totalReimbursements();
		String grade_received= "N/A";
		int grade_attachment_bit = 0;
		
		Reimbursement reim = new Reimbursement(rei_id, empid, fname, lname, dateof, time, location, desc, cost, gradingFormat, 
												typeOfEvent, work, grade_received, grade_attachment_bit, attachment_bit);
		
		reimDao.addReimbursement(reim);
		return rei_id;
	}
	
	public static int editReimbursement(int rei_id, int empid, String fname, String lname, String dateof, String time, String location,
			String desc, int cost, String gradingFormat, String typeOfEvent, String work,  int attachment_bit) {
	
		checkNewYear();
		
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		
		String grade_received= "N/A";
		int grade_attachment_bit = 0;
		
		Reimbursement reim = new Reimbursement(rei_id, empid, fname, lname, dateof, time, location, desc, cost, gradingFormat, 
				typeOfEvent, work, grade_received, grade_attachment_bit, attachment_bit);
		return reimDao.editReimbursement(reim);
	
	}
	
	public static List<Reimbursement> userReimbursements(int emp_id){
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		return reimDao.getAllReimbursementUser(emp_id);
	};
	
	public static List<Reimbursement> superReimbursements(int emp_id){
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		return reimDao.getSuperReimbursementUser(emp_id);
	};
	
	public static List<Reimbursement> departmentReimbursements(String department){
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		return reimDao.getDepartmentReimbursementUser(department);
	};
	
	public static List<Reimbursement> allReimbursements(){
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		return reimDao.getAllReimbursement();
	};
	
	public static int getCostAmount(int emp_id) {
		checkNewYear();
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		return reimDao.getAmountByEid(emp_id);
	}
	
	public static void insertCustomGradingFormat(int reid, int empid, String fname, String lname, String customFormat, String formatDesc) {
		CustomGrade cust = new CustomGrade(reid, empid, fname, lname, customFormat, formatDesc); 
		CustomGradeDaoImpl custDao = new CustomGradeDaoImpl();
		custDao.addCustomFormat(cust);
	}
	
	public static List<CustomGrade> getCustomGrading(int reid){
		CustomGradeDaoImpl custDao = new CustomGradeDaoImpl();
		return custDao.getCustomFormatById(reid);
	};
	
	public static List<CustomGrade> getAllCustomGrading(int emp_id){
		CustomGradeDaoImpl custDao = new CustomGradeDaoImpl();
		return custDao.getAllCustomFormatById(emp_id);
	};
	
	public static int addStatus (int reiid, String afname, String alname, int status, String note) {
		StatusUpdateDaoImpl su = new StatusUpdateDaoImpl();
		return su.updateStatus(reiid, afname, alname, status, note);
	}
	
	public static int addAttachment (Attachments a) {
		AttachmentDaoImpl attDao = new AttachmentDaoImpl();
		return attDao.insertAttachment(a);
	}
	
	public static int totalAttachmentsPerReiId(int id) {
		AttachmentDaoImpl attDao = new AttachmentDaoImpl();
		return attDao.getCountById(id);
	}
	
	public static int getAidByRid(int rei_id) {
		AttachmentDaoImpl attDao = new AttachmentDaoImpl();
		return attDao.getAid(rei_id);
	}
	
	public static File getAttachmentByAid(int a_id) {
		AttachmentDaoImpl attDao = new AttachmentDaoImpl();
		return attDao.getFile(a_id);
	}
	
	public static String getAttachmentNameByAid(int a_id) {
		AttachmentDaoImpl attDao = new AttachmentDaoImpl();
		return attDao.getFileName(a_id);
	}
	
	public static String getAttachmentTypeByAid(int a_id) {
		AttachmentDaoImpl attDao = new AttachmentDaoImpl();
		return attDao.getFileType(a_id);
	}
	
	
	public static int addGrade(Attachments attachment,String grade_received, int grade_bit) {
		GradeDaoImpl graDao = new GradeDaoImpl();
		
		if(grade_bit == 1) {graDao.insertGradeAttachment(attachment);}
		else {}
		return graDao.insertGrade(grade_received, attachment.getRei_id(), grade_bit);
	}
	
	
	
	public static int getGaidByRid(int rei_id){
		GradeDaoImpl graDao = new GradeDaoImpl();
		return graDao.getAid(rei_id);
	}
	
	public static String getGradeAttachmentNameByAid(int a_id) {
		GradeDaoImpl graDao = new GradeDaoImpl();
		return graDao.getFileName(a_id);
	}
	
	
	public static String getGradeAttachmentTypeByAid(int a_id) {
		GradeDaoImpl graDao = new GradeDaoImpl();
		return graDao.getFileType(a_id);
	}
	
	
	public static File getGradeAttachmentByAid(int a_id) {
		GradeDaoImpl graDao = new GradeDaoImpl();
		return graDao.getFile(a_id);
	}
	
	public static List<Status> statusReimbursements(int emp_id){
		StatusUpdateDaoImpl staDao = new StatusUpdateDaoImpl();
		return staDao.getReimbursements(emp_id);
	};
	
	public static List<Status> statusAWReimbursements(String role){
		StatusUpdateDaoImpl staDao = new StatusUpdateDaoImpl();
		return staDao.getAwaitingReimbursements(role);
	}
	
	
	public static void reimburseEmployee(int reiid, int amount) {
		ReimbursementDaoImpl reimDao = new ReimbursementDaoImpl();
		Employee e = reimDao.getEmployeeByRid(reiid);
		
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		empDao.reimburseAmount(amount, e.getEmp_id());
	}
	
	
	
	
	public static void checkNewYear(){
	
	LocalDate localDate = LocalDate.now();
	String date = DateTimeFormatter.ofPattern("MM/dd").format(localDate);
	
	if(date.equals(01/01)) {
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		empDao.refreshAmount();
		
		}
	
	}
}
