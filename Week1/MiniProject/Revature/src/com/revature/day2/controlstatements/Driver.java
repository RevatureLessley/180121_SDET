package com.revature.day2.controlstatements;

public class Driver {

	public static void main(String[] args) {

		/*
		 * Control statements are any block of code that can control the flow of
		 * the application. IE, different branches, loops, etc.
		 */

		/*
		 * Examples For loop for(increment; condition; increment block)
		 */

		for (int i = 5; i > 0; i--) {
			System.out.println(i);
		}
		int j = 5;
		System.out.println(j++);

		// Enhanced For loop
		/*
		 * Note: Arrays are a collection of same typed values. Arrays are
		 * denoted by using square brackets anywhere in the reference.
		 */
		int[] ints = new int[5];
		int[] ints2 = { 1, 2, 3, 4, 5 };

		System.out.println("Enhanced for loop");
		for (int item : ints2) {
			System.out.println(item);

			/*
			 * A while loop continues looping until the condition is no longer
			 * met.
			 */

			System.out.println("While Loop");
			int k = 0;
			while (k < 20) {
				System.out.println(++k);
			}
			/*
			 * The difference between a while and a do while is that the action
			 * will occur before the condition is checked.
			 */
			System.out.println("Do While Loop");
			k = 0;
			do {
				System.out.println(k++);
			} while (k < 20);

		}

		/*
		 * Using switch statement to cover multiple different values.
		 */

		int option = 3;
		switch (option) {
		case 1:
			System.out.println("Case 1");
			break; // Breaks out of any control statement entirely
		case 2:
			System.out.println("Case 2");
			break;
		case 3:
			System.out.println("Case 3");
			break;
		default:
			System.out.println("no matches");
			break;
		}

		// break vs continue
		for (int l = 0; l < 100; l++) {
			if (l == 50) {
				break; // breaks out of the loop entirely.
			}
			if (l % 2 == 0) {
				continue; // skips the current loop.
			}
			System.out.println(l);
		}
	}

}
