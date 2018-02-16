package com.adminend;

import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.ObjectInputStream;


import com.userend.BankAccount;

/**
 * 
 * @author Amr Hosny
 * Class cotains all options for the single Admin that already exist to validate new 
 * Registers accounts
 */
public class AdminMenu implements Serializable {

	private static final long serialVersionUID = -5727973983109106738L;
	final static Logger logger = Logger.getLogger(AdminMenu.class);

	public void showMenu(AdminAccount admin) {

		Scanner input = new Scanner(System.in);
		int selection = 0;
		while (selection != 2) {
			System.out.println("===================================================");
			System.out.println("\n------- Enter one of the following options ------- \n");
			System.out.println("1:  Approve User Account");
			System.out.println("2:  Exit" + "\n");
			System.out.println("===================================================");
			
			System.out.print("Enter Option Here: ");
			selection = input.nextInt();

			switch (selection) {
			case 1:
				System.out.println("===================================================");
				System.out.println("\n-------------- Approve User Account ---------------\n");
				System.out.print("Enter Account Username: ");
				Scanner uName = new Scanner(System.in);
				String userName = uName.next();

				ObjectInputStream ois = null;
				try {
					ois = new ObjectInputStream(new FileInputStream(userName + ".ser"));
					BankAccount user = (BankAccount) ois.readObject();
					user.setAdminApproval(true); // approving user accounts
					System.out.println("\n		ACCOUNT APPROVED \n");
					logger.info("Account" + userName + "Approved"); // Logger info
					reSerializeObject(user);

				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					if (ois != null) {
						try {
							ois.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;

			case 2:
				if (input != null)
					input.close();

				System.exit(0);
			}
		}

	}
	
	/**
	 * 
	 * Reserializing the object to update the new registed user account to validate their accounts 
	 * and allow them to operate theitr accounts
	 */

	public void reSerializeObject(BankAccount user) {

		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(new FileOutputStream(user.getUserName() + ".ser"));

			os.writeObject(user);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
