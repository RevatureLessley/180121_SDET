package Connections;

public interface EmployeeDao {
	public void insertEmployee(Employee employee);
    public Employee getEmployee(String username, String password);
    public Employee getEmployee(int id);
    public boolean updateEmployeeByID(Employee employee);
}
