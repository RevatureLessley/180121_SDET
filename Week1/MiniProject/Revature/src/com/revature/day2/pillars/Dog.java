package com.revature.day2.pillars;

//The only modifiers for a class are public, final or Abstract.
// If your class file has more than one class, other classes can be default as well.
// Every class file must have a public class that is named the same as the file. 
public abstract class Dog extends Animals {
	
	/*
	 * Overriding is where you take a method from a parent class and give it your own implementation
	 * for use in the sub class. In the case for @Override, an error is thrown if it is above a method 
	 * that doesn't exist within a parent hierarchy. 
	 */
	public String secret = "Dog";
	
	@Override
	public String whatAmI() {
		return "I am a dog";
	}
	
	public abstract boolean isDomesticated();
	
	@Override
	public String getSpecies() {
		return "Doge";
	}

}
