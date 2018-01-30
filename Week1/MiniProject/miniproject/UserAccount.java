package com.miniproject;

import java.util.Scanner;

import com.miniproj2.Create_account;
//import com.revature.day4.logging.Driver;

import org.apache.log4j.Logger;


public class UserAccount {

	final static Logger logger = Logger.getLogger(UserAccount.class);
	UserAccount u = new UserAccount();
	//u.log("Test Log");

public void log(String message){
		
		logger.trace(message);
		logger.debug(message);
		logger.info(message);
		logger.warn(message);
		logger.error(message);
		logger.fatal(message);
	}
	public static void main() {
	Create_account user = new Create_account("user",0,"savings"); // initilaize -- name,,Balance,Type
	
	int balance=0;
	int withd=0;
	int cb=0;
	Scanner in = new Scanner(System.in);
  
    
   		int userChoice;
    	boolean quit = false;

    do {
          
          System.out.println("1. Deposit money");
          System.out.println("2. Withdraw money");
          System.out.println("3. Check balance");
          System.out.println("4. Display Account Details");
          System.out.print("Enter Your Choice : ");
          userChoice = in.nextInt();
          switch (userChoice) {
              
          
        case 1: // deposit
           
         System.out.print("Enter Amount Of Money : ");
         balance=in.nextInt();
         user.Acc_Balance=balance;
         System.out.println("\t Successfully Deposited.");
                      
          break;     
            
          case 2: // withdraw money                      
                                  
                     if(user.Acc_Balance==0)
                     System.out.print("Your Account is Empty.");
                     
                     else{
                     System.out.print("Enter Amout Of Money : ");   
                     withd=in.nextInt();  
                     
                     if(withd>user.Acc_Balance){
                     System.out.print("Enter Valid Amout of Money : ");
                     withd=in.nextInt();
                     }
                     else
                     cb= user.withdraw(withd);
                     System.out.println("Your Current Balance : "+cb);   
                     }
                  case 3: // check balance 

                     
                     System.out.println("Your Current Balance : "+user.Acc_Balance);
                     
                                             
              break;
              
          case 4: // display all info 
                  
                                        
                     user.display_details();                             
                
              break;
    				default:
    						System.out.println("Wrong Choice.");
    				break;
  }
    					System.out.println("\n");
						} while (!quit);
							System.out.println("Thanks !");
							if(in != null){
								in.close();
							}
					}

	
		// TODO Auto-generated method stub
		
	

}
