package piggy.bank;

import org.apache.log4j.Logger;

public class BankAccount {
	
	final static Logger logger= Logger.getLogger(BankAccount.class);

	private double balance;
	String bankType;
	String logging;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setBankType(String banktype) {
		this.bankType = banktype;
	}

	public void withdraw(double amount) {
		if(amount < balance) {
			balance -= amount;
			System.out.println("You have withdrawn: " + amount);
		}else {
			System.out.println("Insufficent funds");
		}
	}

	public void deposit(double amount) {
		balance += amount;
		System.out.println("You have deposited: " + amount);
	}

	public void username(String logging) {
		this.logging = logging;
	}

	public void password(String logging) {
		this.logging = logging;
	}
	public void login(String logging) {
		
	}
}
