package com.revature.day3.shortcircuit;

public class Driver {

	public static void main(String[] args) {
		/*
		 * In java there are operators and comparators.
		 * What are some operators?:
		 * +,-,*,/,%,<<,>>
		 * What are some comparators?:
		 * ==, !=, <,>,<=,>=, etc
		 * 
		 * There are specific operators that can combine other operators into
		 * one solid true or false:
		 * bitwise operators:
		 * | and or statement
		 * & which is an and statement.
		 */
		System.out.println(returnTrue());
		System.out.println(returnFalse());
		System.out.println("=====");
		System.out.println(returnTrue() | returnFalse());
		System.out.println(returnTrue() & returnFalse());
		System.out.println("=====");
		System.out.println(returnTrue() & returnFalse() & returnTrue());
		
		/*
		 * Short circuit operators
		 * -A short circuit operator is like its bitwise counterpart except
		 * it will not waste time checking the state of other booleans if
		 * it can determine with 100% that the overall comparison will return true
		 * or false.
		 * 
		 * || = short circuit OR
		 * && = short circuit AND
		 */
		
		System.out.println("========");
		System.out.println(returnTrue() || returnFalse());
		System.out.println(returnFalse() && returnTrue());

		System.out.println(returnTrue() | returnFalse());
		System.out.println(returnFalse() & returnTrue());
	}
	public static boolean returnTrue(){
		System.out.println("RETURNED TRUE");
		return true;
	}
	public static boolean returnFalse(){
		System.out.println("RETURNED FALSE");
		return false;
	}
}