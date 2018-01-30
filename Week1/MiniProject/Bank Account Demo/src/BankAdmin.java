import java.util.Scanner;

public class BankAdmin {

	public static void main(String[] args) {
	
	Scanner imput = new Scanner(System.in);
		
		String user, pass;
		
		System.out.print("Enter your email address  ");
		pass = imput.nextLine();
		
		System.out.print("Enter your username: ");
		user = imput.nextLine();
		
		System.out.print("Enter your password  ");
		pass = imput.nextLine();

		System.out.print("Welcome to Admin  ");
		pass = imput.nextLine();
		
		System.out.print("Would you like to verify a user?  ");
		pass = imput.nextLine();
		
		System.out.print("Enter User name  ");
		pass = imput.nextLine();
		
		if(user.equals("Mike") && (pass.equals("Jedi"))) {
			System.out.println("This User is Verified");
			
		}else {
			System.out.println("This user does not exist ");
			
		}
	}

}
