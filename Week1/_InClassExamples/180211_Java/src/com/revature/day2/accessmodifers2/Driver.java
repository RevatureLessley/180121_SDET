package com.revature.day2.accessmodifers2;
/*
 * In order to access classes from other packages, you need to IMPORT them.
 *NOTE : use 
*/

import com.revature.day2.accessmodifiers.Silence;

public class Driver extends Silence {

	public static void main(String[] args) {
		
		
		Silence silence = new Silence ();
		System.out.println("=====ACCESS VIA METHOD IN CLASS====");
		silence.outputVariables();
		/*
		 * When outside the package, and not a subclass, you cannot access anything
		 * unless it it is public.
		 */
		System.out.println("=====ACCESS VIA METHOD IN CLASS====");
	//	System.out.println(silence.priv); // cannot access private from outside a class
	//	System.out.println(silence.def); // Cannot access, outside of package
	//	System.out.println(silence.prot);// Cannot access, outside of package
		System.out.println(silence.pub);
	
	/*
	 * Using an instance of a class rthat EXTENDS the silence class.
	 * I get access to all PUBLIC and PROTECTED fields and methods.
	 * defaults are omitted however.
	 */
		Driver d = new Driver(); 	 
	//	System.out.println(d.priv;); // Not valid
	//	System.out.println(d.def;); //Not valid
		System.out.println(d.prot);
		System.out.println(d.pub);
	}
	
	}

