package Connections;

public class Employee {
	private int id;
	private String username;
	private String password;
	private int claim_max;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getClaim_max() {
		return claim_max;
	}
	public void setClaim_max(int claim_max) {
		this.claim_max = claim_max;
	}
	public Employee(int id, String username, String password, int claim_max) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.claim_max = claim_max;
	}
	public Employee(String username, String password, int claim_max) {
		super();
		this.username = username;
		this.password = password;
		this.claim_max = claim_max;
	}
	
	public Employee(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public Employee() {
		super();
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", username=" + username + ", password=" + password + ", claim_max=" + claim_max
				+ "]";
	}
	
	
}
