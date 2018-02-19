package dao;

import bean.Employee;

public interface EmployeeDao {
	public boolean createEmployee(String username, String password, int type);
	public Employee logIn(String username, String password);
	public String getRandomHead(int type);
	public String getHead(String username);
	public int getLevel(String username);
}
