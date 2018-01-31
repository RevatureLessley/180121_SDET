package com.revature.day2.pillars;

/*
 * Interfaces should be used to enforce actions of classes.
 * You can use interfaces simply to enforce specific methods to be implemmented 
 * for classes that implement it. But they should enforce actions.
 */

/*
 * A type of class that cannot have any method implemmentation.
 * Any interface variable must be public static final.  
 * As of java 8, you are now allowed to make static methods
 * You are now given a default keyword which allows you to implement an interface method.
 */

/*
 * Interfaces vs Abstract Classes
 * 
 *Both can have, both, concrete and abstract methods.
 * Both cant be instantiated as a standalone instance.
 * Interfaces are used for actions
 * Abstract classes are used as a class to be extended from.
 * You can extend a class, but you must implement an interface.
 * Can only extend one class at most, but you can implement as many interfaces as you want. 
 */
public interface Mammals {

	int i = 2;

	// java implicitly change i to be public, static, final, should you choose
	// to write is as above
	public String speak();

	public String eat();

	public static String ear() {
		return "...yeah...";
	}
	// This is an example of overloading.

	public default String ear(int x) {
		return "this is allowed";
	}

}
