package com.revature.miniproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AccountFile {
//
//	private String file = "Accounts.txt";
//	private String temp = "Account.txt";
	private File file = new File("Accounts.txt");
	private File temp = new File("Account).txt");
	private static ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
	private BufferedWriter w;
	private BufferedReader r;
	private PrintWriter p;
	private BankAccount a;
	public AccountFile(){
	
			lookAtAccounts();
	
	}
	public ArrayList<BankAccount> getAccounts() {
		return accounts;
	}
	
	public  void addAccounts(BankAccount a)
	{
		if(!accounts.isEmpty())
		{
		for(int i = 0; i < accounts.size(); i++)
		{
			if(accounts.get(i).getUser().equals(a.getUser()))
				System.out.println("duplicates");
			else 
				accounts.add(a);
		}
		}
		else	
			accounts.add(a);
	}
	public void saveAccount(BankAccount a)
	{ 
		addAccounts(a);
		try {
			w = new BufferedWriter(new FileWriter(file,true));

			w.write(a.toString());
			w.newLine();
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void saveAccount()
	{ 
		try {
			w = new BufferedWriter(new FileWriter(file,true));
			if(accounts.size()!=0)
			{
			for(int i = 0; i < accounts.size(); i++)
			{
				w.write(accounts.get(i).toString());
				w.newLine();
			}
			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
	public boolean duplicateCheck(String u)
	{
		boolean check=false;
		if (getAccounts().isEmpty())
		{lookAtAccounts();}
		else
		{
		for(int i = 0; i < getAccounts().size(); i++)
		{
			if(getAccounts().get(i).getUser().equals(u))
			{
				check = true;
				System.out.println("Username cannot be used.");
			}
		}
		}
		return check;
	}
	public void deleteAccount(BankAccount a)
	{
		for(int i = 0; i < accounts.size();i++)
		{
			if(accounts.get(i).getUser().equals(a.getUser()))
			{
				accounts.remove(i);
				rewriteFile();
				System.out.println("ACCOUNT DELETED");
			}
			else 
				System.out.println("Cannot be deleted. Account does not exist.");
		}		
	}
	public void rewriteFile()
	{
		String l;
		String l2;
		
		try{
			r = new BufferedReader(new FileReader(file));
			w = new BufferedWriter(new FileWriter(temp,true));
		while((l= r.readLine()) != null)
		{
			for(int i =0; i < accounts.size();i++)
			{
					if (!accounts.get(i).toString().equals(l));
					{	w.write(l);
					w.newLine();
					}
			}	
			// temp.renameTo(file);	
		}
//		temp.renameTo(file);
		r.close();
		w.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	public void printAccounts()
	{
		for(int i = 0; i<accounts.size();i++)
		{
			System.out.println(accounts.get(i));
		}
	}
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


