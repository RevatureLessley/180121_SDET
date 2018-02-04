package beans;

import java.io.Serializable;

import org.apache.log4j.Logger;

public class User{

	/**
	 * 
	 */
	private String userName;
	private String password;
	private int id;
	private double bank_balance;
	private boolean isApproved;
	
	final static Logger logger = Logger.getLogger(User.class);	
	
	public User() {
		super();
		this.id = (int) Math.floor(Math.random() * 10000);
		logger.debug("New User initialized: " + this.toString());
	}
	
	public User(String userName, String password, double bank_balance) {
		super();
		this.userName = userName;
		this.password = password;
		this.id = (int) Math.floor(Math.random() * 10000);
		this.bank_balance = bank_balance;
		this.isApproved = false;
	}
	
	public User(String userName, String password, double bank_balance, int id) {
		this.userName = userName;
		this.password = password;
		this.id = id;
		this.bank_balance = bank_balance;
		this.isApproved = false;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBank_balance() {
		return bank_balance;
	}
	public void setBank_balance(double bank_balance) {
		this.bank_balance = bank_balance;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
	@Override
	public String toString() {
		logger.debug("User printed: " + "userName=" + userName + ", password=" + password + ", id=" + id + ", bank_balance=" + bank_balance
				+ ", isApproved=" + isApproved);
		return "userName=" + userName + ", password=" + password + ", id=" + id + ", bank_balance=" + bank_balance
				+ ", isApproved=" + isApproved;
	}
	
}
