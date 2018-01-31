package com.revature.day2.pillars;

/*
 * When you make an abstrat class, you put the class in a state where it may never be 
 * instantiated. This allows us to provide methods without implementation. we ARE allowed to provide methods with 
 * implementation as well. The purpose of an abstract class is to provide a class that you want 
 * all children to inherits its methods from.
 */
public abstract class Animals {

	public String secret = "Animal";
	
	public String whatAmI(){
		return "I am an animal! Nothing to specific...";
	}
	
	//The abstract keyword for methods enforces that the first non abstract class
	// that inherits this class must implement it.
	public abstract String getSpecies();
}
