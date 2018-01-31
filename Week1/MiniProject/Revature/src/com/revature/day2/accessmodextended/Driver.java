package com.revature.day2.accessmodextended;

/*
 * use ctrl+shift+o to bring in required packages quickly
 */
import com.revature.day2.accessmodifiers.Silence;

public class Driver extends Silence{

	public static void main(String[] args){
		
		Silence sil = new Silence();
		System.out.println("====ACCESS VIA METHOD IN CLASS====");
		sil.outputVariables();
		
		System.out.println("====ACCESS VIA METHOD OUTSIDE CLASS, WITHIN PACKAGE ====");
		
		/*
		 * When outside the package, and not in subclass, you cannot access anything unless its public.
		 */
		
		//System.out.println(sil.def); //Cannot Access
		//System.out.println(sil.prot); // Cannot Access
		System.out.println(sil.pub);
		//System.out.println(sil.priv); // Cannot access private from outside class
		
		Driver d = new Driver();
		System.out.println(d.prot);
		System.out.println(d.pub);
		
	}
}
