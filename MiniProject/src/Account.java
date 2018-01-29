/*
 * Bank database will store account info in the following format:
 * username password checkings savings isAdmin canAccess
 */
public class Account {
	private String username;
	private String password;
	private double checkingsBalance;
	private double savingsBalance;
	private boolean isAdmin;
	private boolean canAccess;
	
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

	public double getCheckingsBalance() {
		return checkingsBalance;
	}

	public void setCheckingsBalance(double checkingsBalance) {
		this.checkingsBalance = checkingsBalance;
	}

	public double getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(double savingsBalance) {
		this.savingsBalance = savingsBalance;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public boolean getCanAccess() {
		return canAccess;
	}

	public void setCanAccess(boolean canAccess) {
		this.canAccess = canAccess;
	}

	public String toString() {
		return username + " " + password + " " + checkingsBalance + " " + savingsBalance + " " + isAdmin + " " + canAccess;
	}
}
