package com.revature.day2.pillars;

/*
 * Interfaces should be used to enforce actions of classes.
 * This is by no means a law, you can use interfaces simply to enforce specific
 * methods to be implemented for classes that implement it.
 * But typically, they should enforce action.
 * 
 * As of Java 8, things get kinda weird with interfaces.
 * For one, we can define an interface as such:
 * -A type of class that cannot have any method implementation.
 * -Any interface variable MUST be public static final.
 * -As of Java 8
 * 		-You are now allowed to make static methods.
 * 		-You are now given a 'default' which allows you to implement an interface method.
 */
public interface Mammalable {
	int i = 2;
	//Java will implicitly change i to be public, static and final, should you choose
	//to write is as above.
	public String speak();
	public String eat();
	
	public static String ear(float f, int x){
		return "...yeah...";
	};
	/*
	 * This is an example of overloading.
	 * When you have 2 or more methods with the same name in the same class, you are
	 * overloading them. The only requirement is that the input parameters are different,
	 * otherwise java will not know which method you are rerferencing by name.
	 */
	public default String ear(int x, float f){
		return "...is this allowed?";
	}
}
