package com.revature.day2.exceptions;

public class Driver {

	public static void main(String[] args) {
		Driver d = new Driver();
		// d.ohBoy();
		d.ohYay();
	}

	/*
	 * This method will throw an arithmetic exception since we divide by 0. An
	 * exception can safely be described as an incident where something
	 * unexpected occurs. When an application behaves in a manner it should not.
	 * 
	 * An exception is not an error. Both are two separate classes. An error can
	 * be descried as: An incident that cannot be reasonably recovered from.
	 * -Think: OutofMemory and StackOverflow errors.
	 * 
	 */
	public void ohBoy() {
		System.out.println(1 / 0);
	}

	/*
	 * we can recover from exceptions -try/catching the exceptions
	 * -ducking/propagating the exception.
	 */

	// catching
	public void ohYay() {
		try {
			System.out.println(1 / 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Made it to the end");
	}

	public void propagate1() {
		
	}

	/*
	 * Any example that is checked java is looking for it.
	 * A checked exception, is any exception that is looked for at compile time and demands
	 * you code a handler for it before you catually running the app.
	 */
}
