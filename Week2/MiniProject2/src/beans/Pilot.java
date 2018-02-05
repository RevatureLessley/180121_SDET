package beans;

public class Pilot {

	private String name;
	private int id;
	private String password;
	
	
	
	public Pilot() {
		super();
	}
	public Pilot(int id) {
		super();
		this.id = id;
	}
	public Pilot(String name, int id, String password) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return name + ": id=" + id + ", password=" + password + "\n";
	}
	
	
}
