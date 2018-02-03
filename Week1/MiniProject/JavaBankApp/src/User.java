import java.util.ArrayList;

public class User {

	private String firstname;
	private String lasttname;
	private String SSNtname;
	private String dOB;
	private String email;
	private UserAccount UserAccount;

	public User(String firstname, String lastname, String sSN, String dOB, String email, UserAccount account) {
		this.firstname = firstname;
		this.lasttname = lastname;
		this.SSNtname  = sSN;
		this.dOB       = dOB;
		this.email     = email;
		this.UserAccount = account;
		
	}
	@Override
	
	public String toString() {
		return "User Information" +
				"Firs Name" + firstname +  
				"last Name" + lasttname + "\n" +
				"SSN"       + SSNtname  + "\n" +
				"DOB"       + dOB       + "\n" +
				"Email"     + email     + "\n" +
				"User Account" + UserAccount;
	}
	public String basicinformation() {
		return 
				"Firs Name" + firstname +  
				"last Name" + lasttname + "\n" +
				"SSN"       + SSNtname  + "\n" +
				"DOB"       + dOB       + "\n" +
				"Email"     + email     + "\n" +
				"User Account" + UserAccount;
	}
	 UserAccount getUserAccount() {
		 return getUserAccount();
	 }

}
