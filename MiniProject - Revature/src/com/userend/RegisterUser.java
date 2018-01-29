package com.userend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/** 
 * @author Amr Hosny
 * Registering a new user by getting the new users first/last names,
 * a choosen username and password
 *
 */
public class RegisterUser implements Serializable {

	private static final long serialVersionUID = 25465484658L;

	public void registeration(String fname, String lname, String username, String pw, int balance,
			boolean adminApproval) throws IOException {

		BankAccount user = new BankAccount(fname, lname, username, pw, balance, adminApproval);
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(new FileOutputStream(username + ".ser"));

			os.writeObject(user);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
}
