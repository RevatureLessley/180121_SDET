package com.revature.beans;

public class Employee {
	private Integer empId;
	private String empName;
	private Integer empSalary;
	private String title;
	
	public Employee() {

	}
	
	public Employee(Integer empId, String empName, Integer empSalary, String title) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
		this.title = title;
	}
	
	public Employee(String empName, Integer empSalary, String title) {
		super();
		this.empName = empName;
		this.empSalary = empSalary;
		this.title = title;
	}
	
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(Integer empSalary) {
		this.empSalary = empSalary;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empSalary=" + empSalary + ", title=" + title
				+ "]";
	}
	
	
}
