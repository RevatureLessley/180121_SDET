
public class checking extends UserAccount {
	private static String AccountType = "checking";
	
	checking(double DirectDeposit){
		//calling parent User Account to use set balance
		super();
		this.setBalance(DirectDeposit);
		this.Interest();
		}
	
	@Override
	public String toString() {
		//Used a to String method to return my a representation of my objects
		//I had to use override because I wanted to show more object representation
		//(\n) means next line format
		return  "AccountType" + AccountType + "UserAccount" +
				"accountnumber" + this.getaccountnumber() + "\n" +
				"Balance" + this.getbalance() + "\n" +
				"Interest Rate" + this.getInterest() + "%\n";
	}
}
