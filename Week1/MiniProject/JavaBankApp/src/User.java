
public class User {

	private final String firstname;
	private final String lasttname;
	private final String SSNtname;
	private final String DOB;
	private final String email;
	private final UserAccount UserAccount;

	public User(String firstname, String lastname, String SSN, String DOB, String email, UserAccount account) {
		this.firstname   = firstname;
		this.lasttname   = lastname;
		this.SSNtname    = SSN;
		this.DOB         = DOB;
		this.email       = email;
		this.UserAccount = account;
		
	}
	@Override
	
	public String toString() {
		return "User Information" +
				"Firs Name" + firstname +  
				"last Name" + lasttname + "\n" +
				"SSN"       + SSNtname  + "\n" +
				"DOB"       + DOB       + "\n" +
				"Email"     + email     + "\n" +
				"User Account" + UserAccount;
	}
	public String basicinformation() {
		return 
				"Firs Name" + firstname +  
				"last Name" + lasttname + "\n" +
				"SSN"       + SSNtname  + "\n" +
				"DOB"       + DOB       + "\n" +
				"Email"     + email     + "\n" +
				"User Account" + UserAccount;
	}
	 UserAccount getUserAccount() {
		 return getUserAccount();
	 }

}
