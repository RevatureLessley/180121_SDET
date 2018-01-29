package piggy.bank;

public class Savings extends BankAccount{
	private static String accountType = "savings";
	
	public Savings(double initialDeposit) {
		this.setBalance(initialDeposit);
	}

}
