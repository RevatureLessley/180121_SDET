package com.revature.day3.scanner;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver {

	public static void main(String[] args) {
		/*
		 * A scanner is a class used to parse strings.
		 * e.g. parse numbers/letters/etc from strings easily.
		 * 
		 * More commonly used for user input in console applications.
		 * By default, scanner will delimit input by spaces ' '
		 * (Delimit meaning how is separates the values within the input.)
		 * 
		 * System.in is used to take user input.
		 */
		
		Scanner in = new Scanner(System.in);
/*		System.out.println("Input anything at all...\n");
		System.out.println(in.nextLine());*/
		
		int i;
		while(true){
/*			String input = in.nextLine();
			if (input.equals("exit")){
				System.out.println("Goodbye!");
				break;
			}else{
				System.out.println(input);
			}*/
			i = 0;
			System.out.println("Start point");
			try{
				i = in.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Input must ONLY be numbers!");
				in.next();
			}
			//Parse out the first instance of a number
			if(i != 0){
				System.out.println("I inputted the number: " + i);
			}
			if(i == -1){
				break;
			}
			
		}
		if(in != null){
			in.close();
		}
		Driver d = new Driver();
		d.scannerExample2();

	}
	
	/*
	 * Another way to take input 
	 */
	public void scannerExample2(){
		Scanner scan = new Scanner(System.in);

		String input = scan.nextLine();
		StringTokenizer st = new StringTokenizer(input);
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			
			System.out.println(token);
		}

		if(scan != null){
			scan.close();
		}
	}


}
