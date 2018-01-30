import java.io.File;
import java.util.Scanner;
import java.util.*;

public class BankMain {

	
	private static Scanner x;
	
	public static void main(String[] args) {
			
		Scanner imput = new Scanner(System.in);
		
		String user, pass;
		
		System.out.print("Enter your email address?  ");
		pass = imput.nextLine();
		
		System.out.print("Enter your username: ");
		user = imput.nextLine();
		
		System.out.print("Enter your password  ");
		pass = imput.nextLine();
		
		if(user.equals("Mike") && (pass.equals("Jedi"))) {
			System.out.println("Welcome User to your Bank Account");
			
		}else {
			System.out.println("These are not yours");
			
		}
		
		Scanner a = new Scanner(System.in);
		System.out.println("How much will you like to deposit?");
		String k = a.nextLine();
		
		
		BankAccount cust1 = new BankAccount();
		cust1.deposit(500);
		
		BankAccount cust2 = new BankAccount();
		cust2.withdraw(100);
		
		System.out.print("You have a checking balance of ");
		System.out.println(cust1.getBalance());
		
		System.out.print("You have a savings balance of ");
		System.out.println(cust2.getBalance());
		
		String username = "Mike";
		String password = "Jedi";
		String filepath = "verify.txt";
		
	}
	public static void verify(String username, String password, String filepath) {
		boolean found = false;
		String tempUsername = "";
		String tempPassword = "";
		
		try {
			x = new Scanner(new File(filepath));
			x.useDelimiter("[,\n");
			
			while(x.hasNext() && !found); {
				tempUsername = x.next();
				tempPassword = x.next();
				
				if(tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim())) {
					found = true;
				}
			}
	}
		catch(Exception e) 
	{

}
}



		
		
	}


