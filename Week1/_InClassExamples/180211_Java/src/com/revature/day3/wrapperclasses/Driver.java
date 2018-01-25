package com.revature.day3.wrapperclasses;

public class Driver {

	public static void main(String[] args) {
		/*
		 * In java there are functions and classes that utilize Objects specifically.
		 * In such methods, it was hard to utilize numbers and characters since they
		 * were primitive datatypes, not objects. 
		 * So java offered an object representatio of every datatype.
		 * These are called the wrapper classes as they wrap around the primitive
		 * datatypes.
		 */
		
		Integer i = new Integer(25);
		Double d;
		Character c;
		Byte b;
		Boolean bool;
		Float f;
		Short s;
		Long l;
		//The classes are conveniently named after the primitive they represent.
		//Just simply they are pascal cased.
		/*
		 * Each of these classes provide useful utility methods for interacting with the
		 * specified primitive value.
		 */
		
		System.out.println(i.MAX_VALUE);
		
		/*
		 * The wrapper classes have static methods, therefor you can access directly
		 * any time you want should want to parse specific from strings for example.
		 */
		
		System.out.println(Integer.parseInt("345345345"));
		
		/*
		 * As of Java 6, autoboxing was introduced.
		 * I was inconvenient to have to create a 'new' Integer for use as an object.
		 * Autoboxing is where a primitive is automatically converted into its 
		 * wrapper class if needed.
		 * Auto-unboxing is where a wrapper class is converted implicitly to its 
		 * datatype if needed
		 */
		
		System.out.println(sum(new Integer(5), new Integer(7)));
		System.out.println(sum(10,5));
		
	}
	
	public static int sum(Integer a, Integer b){
		return a + b;
	}

}