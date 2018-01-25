package com.revature.day2.pillars;
/*
 * When you make an abstract class, you put the class in a state where it may
 * never be instantiated. This allows us to provide mehthods without implementation.
 * Bare in mind, we ARE allowed to provide methods WITH implementatioin as well.
 * The purpose of an abstract class is to provide a class that you want all children
 * to inherit its methods form.
 */
public abstract class Animal {
	public String secret = "Animal";
	
	public String whatAmI() {
		return "I am an animal! Nothing too specific...";
	}

	/*
	 * The abstract keyword for methods enforces that the first non abstract class
	 * that inherits this class must impplment it.
	 */
	public abstract String getSpecies();
	
	
	
	
	
}
