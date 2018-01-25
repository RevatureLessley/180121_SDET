package com.revature.day2.pillars;
/*
 * By using the 'extends' keyword, we are able to inherit from another
 * CLASS and receive all of its methods and field. Since this class is ALSO
 * an abstract, we are NOT required to implement any inherited abstract methods.
 */

/*
 * NOTE1: The ONLY modifiers for a class are public, final, or abstract
 * NOTE2: If you class file has more than one class, other classes can be default as well
 * NOSTE3: Every class file MUST have a public class that is name the same as the file.
 */
public abstract class Dog extends Animal {
/*
*Overriding is where you take a method from a parent class and five it your
*own implemention for use in the subclass.
*Note the @Override. This is called an annotation. Annotations provide metadata
*or enforce conventions for methods they are above.
*in the case opf @override, an error is thrown if it is above a mthod that does not
*exist within the parent hierachy.
 */
	public String secret = "Dog";
	
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
