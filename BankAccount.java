
public class BankAccount {

	private double balance;
	
	public BankAccount() {
		balance = 0.0;
	}
	
	public void deposit(double amount) {
		balance = balance + amount;
	}
	
	public void withdraw(double amount) {
		if(amount<=balance) {
			balance = balance - amount;
		}else {
			System.err.println("Transaction cancelled due to insufficient funds");
		}
	}
	
	public double getBalance() {
		return balance;
	}
	
	
	
}
