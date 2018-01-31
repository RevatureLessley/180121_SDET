package com.revature.day3.casting;

public class Driver {

	public static void main(String [] args){
		
		/*
		 * Casting in java is where you will change the dataype of a 
		 * field or object into another. 
		 * 
		 * There are 2 types of casting, implicit casting and explicit casting. 
		 * The only diff between the 2 is where you have to actually type the cast or not.
		 * 
		 */
		
		int i = 5; 
		double d = 5.0;
		
		i = (int)d; //Explicit Casting
		d = i;      //Implicit Casting
		
		/*
		 * If you are placing a larger bit size var into a small bit size datatype then you must explicitly cast it. 
		 * However, if its vice versa then u can implicitly do it. These rules don't apply to all.
		 */
		short s = 5;
		long l = 5_000_000_000L;
		float f = 5.0f;
		
		s= (short)l;
		l=s;
		
		s=(short)i;
		i=s;
		
		d=f;
		f=(float)d;
		
		l=(long)d;
		
		byte b = 5; // 8 bits
		char c = 10; //16 bits 
		
		b=(byte)c;
		c=(char)b; //chars are unsigned
		
		boolean bool = false;
		//= (int)bool; //Can't be casted to anything.
		
	}
}
