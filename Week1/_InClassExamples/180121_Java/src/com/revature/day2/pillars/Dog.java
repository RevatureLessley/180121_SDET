package com.revature.day2.pillars;

//NOTE: the only modifiers for a class are public, final or abstract.
//NOTE2: If your class file has more than one class, other classes can be 
//default as well.
//NOTE3: Every class file MUST have a public class that is named the same as the file.
public abstract class Dog extends Animal{
	/*
	 * By utilizing the 'extends' keyword, we are able to inherit from another
	 * CLASS and receive all of its methods and field. Since this class is ALSO
	 * an abstract, we are NOT required to implement any inherited abstract methods.
	 */
	/*
	 * Overriding is where you take a method from a parent class and give it your
	 * own implentation for use in the sub class.
	 * Note the @Override. This is called an annotation. Annotations provide metadata
	 * or enforce conventions for methods they are above.
	 * In the case of @Override, an error is thrown if it is above a method that does not 
	 * exist within the parent hierarchy.
	 */
	@Override
	public String whatAmI() {
		return "I am a doge";
	}
	
	public abstract boolean isDomesticated();
	
	@Override
	public String getSpecies() {
		return "Doge";
	}

}

