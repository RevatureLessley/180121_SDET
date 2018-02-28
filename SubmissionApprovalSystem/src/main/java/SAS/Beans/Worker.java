
package SAS.Beans;

public class Worker {
	private String fName;
	private String lName;
	private String uName;
	private String pWord;
	private String email;
	private int w_id;
	private int u_id;
	private int superv;
	private double r_aval;
	private int depId;
	private int admin_stat;

	public Worker() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Worker(String fName, String lName, String uName, String pWord, String email, int w_id, int superv, int dep_hed,
			double r_aval, int depId, int admin_stat) {
		super();
	}

	public Worker(String uName, String pWord) {
		super();
		this.uName = uName;
		this.pWord = pWord;
	}

	
	public Worker(int u_id) {
		super();
		this.u_id = u_id;
	}


	public String getfName() {
		return fName;
	}


	public void setfName(String fName) {
		this.fName = fName;
	}


	public String getlName() {
		return lName;
	}


	public void setlName(String lName) {
		this.lName = lName;
	}


	public String getuName() {
		return uName;
	}


	public void setuName(String uName) {
		this.uName =this.getEmail();
	}


	public String getpWord() {
		return pWord;
	}


	public void setpWord(String pWord) {
		this.pWord = pWord;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getW_id() {
		return w_id;
	}


	public void setW_id(int w_id) {
		this.w_id = w_id;
	}


	public int getU_id() {
		return u_id;
	}


	public void setU_id(int u_id) {
		this.u_id = u_id;
	}


	public int getSuperv() {
		return superv;
	}


	public void setSuperv(int superv) {
		this.superv = superv;
	}


	public double getR_aval() {
		return r_aval;
	}


	public void setR_aval(double r_aval) {
		this.r_aval = r_aval;
	}


	public int getDepId() {
		return depId;
	}


	public void setDepId(int depId) {
		this.depId = depId;
	}


	public int getAdmin_stat() {
		return admin_stat;
	}


	public void setAdmin_stat(int admin_stat) {
		this.admin_stat = admin_stat;
	}
	


	
	
	// constructs the worker bean

}
