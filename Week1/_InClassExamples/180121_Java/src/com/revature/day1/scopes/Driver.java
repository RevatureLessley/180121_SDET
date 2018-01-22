package com.revature.day1.scopes;

public class Driver {

	public static void main(String[] args) {
		//When instantiating a class you need to follow the below format:
		//Reference = assignment
		//ClassName varName = new ClassName()
		Person p = new Person();
		/*
		 * The 'new' keyword calls the constructor of any given class.
		 */
		
		System.out.println("Name: " + p.name);
		System.out.println("Age: " + p.age);
	}

}
