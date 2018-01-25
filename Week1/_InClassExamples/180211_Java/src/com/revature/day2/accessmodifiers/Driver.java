package com.revature.day2.accessmodifiers;

public class Driver {

	public static void main(String[] args) {
		
		Silence silence = new Silence ();
		System.out.println("=====ACCESS VIA METHOD IN CLASS====");
		silence.outputVariables();
		
		System.out.println("=====ACCESS VIA METHOD IN CLASS====");
	//	System.out.println(silence.priv); // cannot access private from outside a class
		System.out.println(silence.def);
		System.out.println(silence.prot);
		System.out.println(silence.pub);
	
	
	}

}
