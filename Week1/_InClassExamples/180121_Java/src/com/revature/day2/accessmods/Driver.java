package com.revature.day2.accessmods;

public class Driver {

	public static void main(String[] args) {
		Silence silence = new Silence();
		System.out.println("====ACCESS VIA METHOD IN CLASS====");
		silence.outputVariables();
		
		System.out.println("====ACCESS VIA METHOD OUTSIDE CLASS, WITH PACKAGE====");
		//System.out.println(silence.priv); //Cannot access private from outside class
		System.out.println(silence.def);
		System.out.println(silence.prot);
		System.out.println(silence.pub);
	}

}
