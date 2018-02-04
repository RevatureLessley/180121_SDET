
public class UserAccount {
	//Used Encapsulation to set and get user info
	private double balance = 0;
	private double interest = 0.05;
	private int accountnumber;
	private static int numberAccount = 10000000;
	
	
	UserAccount() {
		//to increment the account number for each account created
		accountnumber = numberAccount++;
	}

	public double getbalance() {
		return balance;

	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterest() {
		return interest *100;
	}

	public void setInterest(double interest) {

	}

	public int getaccountnumber() {
		return accountnumber;
	}
	
	public void withdrawal(double amount) {
		if (amount + 5 > balance) {
			System.out.println("Not Enough Funds");
			return;
		}
		balance -= 5;
		System.out.println("You have withdrawn" + amount);
		System.out.println("Your curent balance" + balance);
		}
	
	public void deposit(double amount) {
		if (amount < 0) {
			System.out.println("You have deposit nothing");
			return;
		}
		Interest();
		amount = amount + amount* interest;
		balance += amount;
		System.out.println("You have deposited" + amount+ "with interest rate" + interest*100 +"%");
		System.out.println("Your curent balance" + balance);
	}

	public void Interest() {
		//reset interest rate based on funds in account balance
		if(balance >10000) {
			interest = 0.08;
		}else {
			interest = 0.05;
		}
	
	}
	
}