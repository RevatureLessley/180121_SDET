package com.miniproject.util;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * Class used to read input and and handle exceptions
 */
public class InputReader {
	private static Scanner input = new Scanner(System.in);
	
	private InputReader() {
		
	}
	
	public static String readString() {
		String str = null;
		try {
			while(str == null) {
				if(input.hasNext()) {
					str = input.next();
/*					if(str.equals("")) {
						str = null;
						System.out.println("you null");
					}*/
				} else {
					input.next();
				}
			}
			
		} catch(NoSuchElementException | IllegalStateException e ) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	public static int readInt(String prompt) {
		return readInt(-1, prompt);
	}
	
	public static int readInt(int falseInt, String prompt) {
		int choice = falseInt;
		try {
			if(input.hasNextInt()) {
				choice = input.nextInt();
			} else {
				if(input.hasNext()) {
					input.next();
				}
			}
		} catch(InputMismatchException | IndexOutOfBoundsException e) {
			System.err.println("Please input a integer number.");
			e.printStackTrace();
		}
		
		while(choice == -1) {
			System.out.println(prompt);
			if(input.hasNextInt()) {
				choice = input.nextInt();
			} else {
				if(input.hasNext()) {
					input.next();
				}
				choice = -1;
			}
		}
		
		return choice;
	}
	
	public static float readFloat(String prompt) {
		Float userInput = null;
		try {
			while(userInput == null) {
				System.out.println(prompt);
				if(input.hasNextFloat()) {
					userInput = input.nextFloat();
				} else {
					if(input.hasNext()) {
						input.next();
					}
				}
			}
			
			
		} catch(InputMismatchException e) {
			e.printStackTrace();
		}
		
		return userInput;
	}
	
	public static void closeReader() {
		if(input != null) {
			input.close();
		}
	}
}
