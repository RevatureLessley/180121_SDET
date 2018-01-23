package com.revature.day2.accessmods2;

import com.revature.day2.accessmods.Silence;

/*
 * In order to access classes from other packages, you need to IMPORT them.
 */
//NOTE: use ctrl+shift+o to bring in required packages quickly.

public class Driver extends Silence{

	public static void main(String[] args) {
		
		Silence silence = new Silence();
		System.out.println("====ACCESS VIA METHOD IN CLASS====");
		silence.outputVariables();
		
		System.out.println("====ACCESS VIA METHOD OUTSIDE CLASS, OUTSIDE PACKAGE====");
		/*
		 * When outside of the package, and not a subclass, you cannot access anything
		 * unless it is public.
		 */
		//System.out.println(silence.priv); //Cannot access private from outside class
		//System.out.println(silence.def); //Cannot access
		//System.out.println(silence.prot); //Cannot access
		System.out.println(silence.pub);
		
		/*
		 * Using an instance of a class that EXTENDS the silence class.
		 * I get access to all PUBLIC and PROTECTED fields and methods.
		 * defaults are omitted however.
		 */
		Driver d = new Driver();
		//System.out.println(d.priv); //Not valid
		//System.out.println(d.def);  //Not valid
		System.out.println(d.prot);
		System.out.println(d.pub);
		
	}

}
