package com.miniproject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ArrayListSerialization implements Serializable{

	public static void main(String [] args)
	   {
	       
		
		ArrayList<String> al=new ArrayList<String>();
	    ArrayList<String> al1=new ArrayList<String>();
	    Scanner in=new Scanner(System.in);
	    System.out.println("Please enter your username and password:");
	    al.add(in.next());
	    al1.add(in.next());
	    Iterator itr=al.iterator();
	    Iterator itr1=al1.iterator();
	    while(itr.hasNext()&& itr1.hasNext())
	    {
	    	if (UserAdmin.authenticate(al, al1)) {
               System.out.println("Hi " + al + "! You are registered and is successfully logged in.");
               
               UserAccount.main();
               break;
	    	}            
	    	else System.out.println("Invalid user");
	    	System.out.println(itr.next());
	    	System.out.println(itr1.next());
	    }
		
	    if(in != null){
			in.close();
		}

	       try{
	         FileOutputStream fos= new FileOutputStream("account.txt");
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(al);
	         oos.writeObject(al1);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	   }


}
