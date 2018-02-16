package com.revature.beans;

public class Employee {
	
	private int emp_id; 
	private String fname;
	private String lname;
	private String username;
	private String password;
	private String email;
	private String role;
	private String department;
	private int sup_id;
	private int amount;
	
	/**Constructor for the Employee class. Requires all possible arguments in order to create an employee object. **/
	public Employee(int emp_id, String fname, String lname, String username, String password, String email, String role,
			String department, int sup_id, int amount) {
		super();
		this.emp_id = emp_id;
		this.fname = fname;
		this.lname = lname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.department = department;
		this.sup_id = sup_id;
		this.amount = amount;
	}
	
	//Setters for the employee class
	/**Sets the emp_id variable of the Employee Object**/
	public void setEmp_id(int emp_id) {this.emp_id = emp_id;}
	/**Sets the fname variable of the Employee Object**/
	public void setFname(String fname) {this.fname = fname;}
	/**Sets the lname variable of the Employee Object**/
	public void setLname(String lname) {this.lname = lname;}
	/**Sets the username variable of the Employee Object**/
	public void setUsername(String username) {this.username = username;}
	/**Sets the password variable of the Employee Object**/
	public void setPassword(String password) {this.password = password;}
	/**Sets the email variable of the Employee Object**/
	public void setEmail(String email) {this.email = email;}
	/**Sets the role variable of the Employee Object**/
	public void setRole(String role) {this.role = role;}
	/**Sets the department variable of the Employee Object**/
	public void setDepartment(String department) {this.department = department;}
	/**Sets the sup_id variable of the Employee Object**/
	public void setSup_id(int sup_id) {this.sup_id = sup_id;}
	/**Sets the amount variable of the Employee Object**/
	public void setAmount(int amount) {this.amount = amount;}
	
	//Getters for the Employee class
	/**Returns the emp_id value of the Employee Object**/
	public int getEmp_id() {return emp_id;}
	/**Returns the fname value of the Employee Object**/
	public String getFname() {return fname;}
	/**Returns the lname value of the Employee Object**/
	public String getLname() {return lname;}
	/**Returns the username value of the Employee Object**/
	public String getUsername() {return username;}
	/**Returns the password value of the Employee Object**/
	public String getPassword() {return password;}
	/**Returns the email value of the Employee Object**/
	public String getEmail() {return email;}
	/**Returns the role value of the Employee Object**/
	public String getRole() {return role;}
	/**Returns the department value of the Employee Object**/
	public String getDepartment() {return department;}
	/**Returns the sup_id value of the Employee Object**/
	public int getSup_id() {return sup_id;}
	/**Returns the amount value of the Employee Object**/
	public int getAmount() {return amount;}
	
}
