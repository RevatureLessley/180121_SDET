package com.JawaunBank;
import java.util.*;
import java.io.*;


public class BankDesk implements Serializable{
	/**
	 * this class serves as the interface for users to interact with the bank
	 */
	private static final long serialVersionUID = 4657641184118068350L;
	protected Scanner texted = new Scanner(System.in);
	protected static final JBBank jbank = JBBank.getBank();
	public static void main(String args[]) {
		try{
			jbank.registerAdmin();
			jbank.signInAdmin(); //sign the admin credentials in
			jbank.initi();
			HashMap<Integer, Account> bankset = jbank.getAccountList();
			jbank.saveAcctData(bankset);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	