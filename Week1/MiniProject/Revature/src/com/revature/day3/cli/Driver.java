package com.revature.day3.cli;

public class Driver {

	/*
	 * In order to use comand arguments: 
	 * navigate to: 
	 * run > run config > (click the arguments tab) > write arguments separated by spaces
	 * 
	 * (The brave way)
	 * 
	 */
	public static void main(String[] args) {
		
		System.out.println("Amount of arguments: " + args.length);
		for (String s : args){
			System.out.println(s);
		}

	}

}
