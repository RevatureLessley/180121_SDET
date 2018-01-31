package com.revature.day3.shortcircuit;

public class Driver {

	public static void main(String [] args){
		
		System.out.println(returnTrue());
		System.out.println(returnFalse());
		System.out.println("=======================");
		System.out.println(returnFalse() | returnTrue());
		System.out.println(returnTrue() & returnFalse());
		System.out.println("===============");
		System.out.println(returnTrue() & returnFalse() & returnTrue());
		
		/*
		 * Short circuit ops
		 * is like its bitwise counterpart except it will not waste time checking the state of other 
		 * booleans if it can determine with 100% certainty that the overall comparison will return 
		 * true or false
		 * 
		 * || = or
		 * && = and
		 */
		
		System.out.println("========");
		System.out.println(returnTrue() || returnFalse());
		System.out.println(returnTrue() && returnFalse());
		
	}
	public static boolean returnTrue(){
		System.out.println("Returned True");
		return true; 
	}
	public static boolean returnFalse(){
		System.out.println("Returned False");
		return false; 
	}
}
