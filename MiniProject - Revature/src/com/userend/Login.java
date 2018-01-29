package com.userend;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import org.apache.log4j.Logger;

import com.adminend.AdminMenu;


/**
 * Class checks username with existing file and password, if authenticated, then it will
 * take the user to the existing user menu
 */
public class Login implements Serializable {

	private static final long serialVersionUID = 94568946541652L;
	final static Logger logger = Logger.getLogger(AdminMenu.class);


	public void confirmLogin(String username, String password) throws IOException {

		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(username + ".ser"));
			BankAccount user = (BankAccount) ois.readObject();

			if (password.equals(user.getPassword()) && user.isAdminApproval()) {

				UserMenu menu = new UserMenu();
				menu.showMenu(user);
			}

			else if (user.isAdminApproval() == false) {
				System.out.println("    ADMIN APPROVAL REQUIRED FOR THIS ACCOUNT \n");
				logger.warn("Account" + user.getUserName() + "need Approval"); // Logger info

			} else {
				System.out.println("Wrong password");
				logger.warn("Account" + user.getUserName() + "Entered Wrong Password"); // Logger info
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				ois.close();
			}
		}
	}

}
