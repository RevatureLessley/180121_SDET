package com.revature.day1.helloworld;

//Double slashes create a single line comment

/*
 * Slash-star creates block comments.
 * ALWAYS comment your code.
 */
public class HelloWorld {
	/**
	 * Slash-star-star creats a java doc. This is 
	 * a way to create a method description that is 
	 * visible when invoking the method.
	 * 
	 * The main method is the method that a java console application requires in order
	 * to run. The method MUST be exactly like the one written below in order to work.
	 * 
	 * The method MUST be:
	 * -public
	 * -static
	 * -return nothing (void)
	 * -lower case methodname of 'main'
	 * -Take a parameter of a String array.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * In order to print to a console in java,
		 * you need to use the System class.
		 *
		 * System.out.println("Hello World!");
		 */
		System.out.println("Hello World!");
		//Shortcuts: syso + [ctrl+space] for print statement
		//Also works with sysout
	}
}
