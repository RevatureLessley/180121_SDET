package com.trms.services;

import java.util.List;

import com.trms.beans.Application;
import com.trms.beans.Employee;
import com.trms.dao.FormDao;

public class FormServices {

	public static boolean addNewForm(Application newApp) {
		newApp.setStatus("1");
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		newApp.setSubmitDate(date);

		FormDao dao = new FormDao();

		if (dao.insertApplication(newApp) != 0)
			return true;
		return false;
	}

	public static List<Application> getFormsByUsername(String username) {
		Employee emp = EmployeeService.getEmpByUsername(username);
		return getFormsByEmpId(emp.getEmpId());
	}

	public static List<Application> getFormsByEmpId(long id) {
		FormDao dao = new FormDao();
		return dao.getFormsByEmpId(id);
	}

	public static List<Application> getSupervisorForms(String username) {
		FormDao dao = new FormDao();
		return dao.getSupervisorForms(username);
	}

	public static void updateSvAction(Long appId, String svDecision, String svComment) {
		FormDao dao = new FormDao();
		dao.updateSvAction(appId, svDecision, svComment);
	}
	
	public static Application getFormByAppId(long id) {
		FormDao dao = new FormDao();
		return dao.getFormByAppId(id);
	}
	
	public static List<Application> getDHForms(String username){
		if(EmployeeService.getEmpByUsername(username).getTitle().equals("Department Head")){
			FormDao dao = new FormDao();
			return dao.getDHForms(username);
		}
		
		return null;
	}
	
	public static List<Application> getBCForms(String username){
		if(EmployeeService.getEmpByUsername(username).getTitle().equals("BenCo")){
			FormDao dao = new FormDao();
			return dao.getBCForms(username);
		}
		return null;
	}
	
	public static List<Application> getAllForms(){
		FormDao dao = new FormDao();
		return dao.getAllForms();
	}

	public static void updateDhAction(Long appId, String Decision,
			String Comment) {
		FormDao dao = new FormDao();
		dao.updateDhAction(appId, Decision, Comment);
		
	}

	public static void updateBcAction(Long appId, String bcDecision,
			String bcComment) {
		FormDao dao = new FormDao();
		dao.updateBcAction(appId, bcDecision, bcComment);
	}
}
