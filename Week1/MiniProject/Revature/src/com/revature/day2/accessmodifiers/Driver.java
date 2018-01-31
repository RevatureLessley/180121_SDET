package com.revature.day2.accessmodifiers;

public class Driver {

	public static void main(String[] args){
		
		Silence sil = new Silence();
		System.out.println("====ACCESS VIA METHOD IN CLASS====");
		sil.outputVariables();
		
		System.out.println("====ACCESS VIA METHOD OUTSIDE CLASS, WITHIN PACKAGE ====");
		System.out.println(sil.def);
		System.out.println(sil.prot);
		System.out.println(sil.pub);
		//System.out.println(sil.priv); // Cannot access private from outside class
	}
}
