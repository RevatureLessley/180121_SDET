package bean;

public class Employee {
	
	//get pending requests via javascript / ajax
	private String username;
	private String password;
	private int type; //0 for employee, 1 for supervisor, 2 for dept head, 3 for BenCo
	
	public Employee() {
		super();
	}
	
	public Employee(String username, String password, int type) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
