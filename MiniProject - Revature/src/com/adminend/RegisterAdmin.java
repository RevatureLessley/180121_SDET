package com.adminend;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
 /**
  * 
  * @author Amr Hosny
  * Class will create only one admin account at the start of the program that will have the
  * username of "Admin" and passwords of "Admin123456789". to login in you just have to type in 
  * the password. Admin Class cannot create a normal user class with the UserName of "Admin"
  */
public class RegisterAdmin implements Serializable {

	private static final long serialVersionUID = 8426842239942687828L;

	public void registeration() throws IOException {

		AdminAccount admin = new AdminAccount("Admin", "Admin", "Admin123456789");
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(new FileOutputStream("Admin.ser"));

			os.writeObject(admin);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}
}