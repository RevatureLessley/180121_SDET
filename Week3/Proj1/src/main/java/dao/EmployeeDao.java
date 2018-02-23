package dao;

import bean.Employee;

public interface EmployeeDao {
	public boolean createEmployee(String username, String password, int type);
	public Employee logIn(String username, String password);
	public String getRandomHead(int type);
	public String getHead(String username);
	public int getLevel(String username);//0 for employee, 1 for supervisor, 2 for dept head, 3 for benco
}
