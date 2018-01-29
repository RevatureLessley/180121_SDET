package com.revature.miniproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

public class AccountFile {

	private String file = "Accounts.txt";
	private String temp = "Account.txt";
	private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	private BufferedWriter w;
	private BufferedReader r;
	private PrintWriter p;
	private BankAccount a;
	public AccountFile(){
	
	}
	public ArrayList<BankAccount> getAccounts() {
		return accounts;
	}
	
	public  void addAccounts(BankAccount a)
	{
	
				accounts.add(a);

	}
	//Write the accounts stored in ArrayList accounts to text file
	public void saveAccount()
	{
		try {
			w = new BufferedWriter(new FileWriter(file,true));
			for(int i =0; i< accounts.size(); i++)
			{
			w.write(accounts.get(i).toString());
			w.newLine();
			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Looks for accounts that are saved in the text file and adds them to account array list
	public void lookAtAccounts() 
	{
		String line;
		if(accounts.isEmpty())
		{
		try{
			r = new BufferedReader(new FileReader(file));
			
			while((line = r.readLine()) != null)
			{
				String[] a = line.split(" ");
				BankAccount b = new BankAccount(a[0], a[1]);
				b.setAmount(Double.parseDouble(a[2]));
				if(a[3].equals("true"))
				b.setApproval(true);
				else
					b.setApproval(false);
				if(!duplicateCheck(a[0]))
				addAccounts(b);
			}
			r.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		}
	}
	//checks for duplicate accounts by username
	public boolean duplicateCheck(String u)
	{
		boolean check=true;
		if(accounts.isEmpty())
		for(int i = 0; i < getAccounts().size(); i++)
		{
			if(getAccounts().get(i).getUser().equals(u))
			{
				check = false;
				System.out.println("Username cannot be used.");
			}
		}
		return check;
	}
	
	public void printAccounts()
	{
		for(int i = 0; i<accounts.size();i++)
		{
			System.out.println(accounts.get(i));
		}
	}
	//returns the bank account the is associated with the username
	public BankAccount checkAccounts(String user)
	{
		BankAccount temp = null;
		for(int i = 0; i< accounts.size(); i++)
		{
			if(accounts.get(i).getUser().contains(user))
				{
			
				temp = accounts.get(i);
				}
		}
		return temp;
		
	}
	//Checks if the username and password inputed is the matches with the account
	public boolean checkLogin(String user,String pw)
	{
		boolean l = false;
		for(int i = 0; i<accounts.size(); i++)
		{
			if(accounts.get(i).getUser().equals(user) && accounts.get(i).getPw().equals(pw))
				l = true;
				
		}
		return l;	
}
	
}


