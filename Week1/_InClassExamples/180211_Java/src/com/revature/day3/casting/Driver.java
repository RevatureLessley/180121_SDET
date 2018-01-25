package com.revature.day3.casting;

public class Driver {

	public static void main(String[] args) {
		/*
		 * Casting in java is where you will change the datatype of a
		 * field or object into another.
		 * There are two types of casting, implicit casting and explicit casting.
		 * The only difference between the two is where you have to
		 * actually type the cast or not.
		 */
		
		int i = 5;
		double d = 5.0;
		
		i = (int)d; //EXPLICIT CASTING
		d = i;      //IMPLICIT CASTING
		/*
		 * If you are placing a larger bit size variable into a small bit size datatype,
		 * then you must explicitly cast it.
		 * however, if you are placing a variable of a smaller datatype into a bigger 
		 * one, you can implicitly do it.
		 * This rule does not apply to all situations as we will see.
		 */
		
		short s = 5;
		long l = 5_000_000_000L;
		float f = 5.0f;
		
		s = (short)l;
		l = s;
		
		s = (short)i;
		i = s;
		
		d = f;
		f = (float)d;
		l = (long)d;
		
		byte b = 5;  //8 bit
		char c = 10; //16 bit
		
		b = (byte)c;
		c = (char)b; //chars are unsigned
		c = (char)-5;
		
		boolean bool = false;
		//i = (int)bool;
	}

}