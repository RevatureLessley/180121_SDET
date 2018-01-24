package com.revature.day3.cli;

public class Driver {

	/*
	 * In order to use command arguments:
	 * (The easy way)
	 * navigate to:
	 * run > run configurations > (click the arguments tab) > write arguments separated by spaces
	 * 
	 * (The brave way)
	 * 1. Navigate to your src folder for your project
	 * 2. Invoke the java compiler via: "javac"
	 * 2.5 e.g. javac package/package/package/file.java
	 * 3. Next execute the application using 'java'
	 * 3.5 e.g. java package.package.package.file arg1 arg2 arg3 etc
	 */
	
	
	
	/*
	 * String[] args is an array of strings to hold any command line arguments that are inputted
	 * at runtime.
	 */
	public static void main(String[] args) {
		System.out.println("Amount of arguments: " + args.length);
		for(String s : args){
			System.out.println(s);
		}
	}

}
