package com.miniprojectbankingsystem.serviceprovider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.miniprojectbankingsystem.services.BankServices;

public class ServiceProvider {

	static Properties properties = null;

	public static BankServices getProvider() {

		try {
			properties = new Properties();
			properties
					.load(new FileReader(".\\src\\resources\\data.properties"));
			String provider = (String) properties.get("provider");
			Class c = Class.forName(provider);
			return (BankServices) c.newInstance();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}