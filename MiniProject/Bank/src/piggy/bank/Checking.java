package piggy.bank;

public class Checking extends BankAccount {
	private static String accountType = "checking";

	Checking(double initialDeposit) {
		this.setBalance(initialDeposit);
	}
}
