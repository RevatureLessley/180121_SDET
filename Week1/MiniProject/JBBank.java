package com.JawaunBank;
import java.io.*;
import java.util.*;
import org.apache.log4j.Logger;


public class JBBank extends Bank implements Serializable{
	
	private static final long serialVersionUID = 4967877267111859829L;

	private static final Logger logger = Logger.getLogger(JBBank.class);
	
	private static JBBank bank = getBank();
	
		
	private JBBank() {
		//blank private constructor to make class a singleton
	}
	
	protected static JBBank getBank(){
        if (bank == null) {
        		bank = new JBBank();
        } //initializes a new J&B Bank if there is none
        return bank;	
	}
	
	@SuppressWarnings("unchecked")
	protected HashMap<Integer, Account> pullAcctData() throws IOException{
		File file = new File ("/JawaunBank/src/com/JawaunBank/Files/AccountList.ser");
		System.out.println(file.exists());
		logger.info("Pulling saved accounts from JBBank");
		HashMap<Integer, Account> bankmap = null;
		if(file.createNewFile() == true) {
			System.out.println("You have created a new storage file!");
		}
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			bankmap = (HashMap<Integer, Account>) ois.readObject();
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
			logger.error(ce);
		}catch(IOException ioe){
            ioe.printStackTrace();
		} finally {
			ois.close(); //close the input byte stream
			fis.close();//close file input
		}
		return bankmap;
	}
	
	protected void saveAcctData(HashMap<Integer, Account> bankMap) throws IOException {
		logger.debug("Bank will now save a copy of your data");
		File file = new File("/JawaunBank/src/com/JawaunBank/Files/AccountList.ser");
		ObjectOutputStream oos = null;
		try{
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(bankMap);
		}finally{
			if(oos!=null){
				oos.close();
			}
		}	
	}
	
	protected void initi() {
		System.out.println("Hello, welcome to J&B bank");
		System.out.println("You are currently logged out. Please sign-in or register");
		System.out.println("Please enter 1 to register or 2 to sign in, 3 to sign in as admin");
		try {
			setAccountList(pullAcctData());
		} catch (IOException e) {
		}
		Integer choice = null;
		try { //checks that input is an int
			choice = Integer.parseInt(iny.next());
			if(choice.equals(1)){
				register2();//lets you register if you click 1, admin for anything else
			} else if (choice.equals(2)) {
				signin(); //lets you sign in if clicking 2
			} else {
				signInAdmin();
			}
			//lets you sign in if clicking 3		
		} catch (InputMismatchException e) {
			System.out.println("please enter a number 1 or 2");
			e.printStackTrace();
			initi();
		}
	}
	protected void register2() {
		System.out.println("Please enter 1 to register Accounts or any number otherwise to register Admins");
		int regi = iny.nextInt(); //allows you to register as an admin or a bank account user
		if(regi==1) {
			register();
		} else {
			try {
				registerAdmin(); //register admin if not user
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
