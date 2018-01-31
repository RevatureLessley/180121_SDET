package com.revature.day3.wrapperclass;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * As of java 6 wrapper classes were implemented. 
		 * In java there are functions and classes that utilize objects specifically.
		 * In such methods, it was hard to utilize #'s and chars since they were prim data types
		 * and not objects. 
		 * So java offered an object representation of every datatype. 
		 * These are called wrapper classes as they wrap around the primitive datatypes
		 */
		
		Integer i = new Integer(25);
		Double d;
		Character c;
		Byte b;
		Boolean bool;
		Float f;
		Short s;
		Long l;
		
	/*
	 * The wrapper classes have static methods, therefore you can access directly at any time you want, should you want to 
	 * parse specific from strings. 
	 * 
	 * As of java 6, autoboxing was introduced. 
	 * It was inconvenient to have to create a new Integer for use as an object.
	 */System.out.println(sum(new Integer(5), new Integer(7)));
		}
	 
	public static Integer sum(Integer a, Integer b){
		return Integer.valueOf(a) + Integer.valueOf(b);
	}
}


