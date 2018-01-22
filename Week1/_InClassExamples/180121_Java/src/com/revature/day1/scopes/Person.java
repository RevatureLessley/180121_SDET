package com.revature.day1.scopes;

//Every class starts with the classname, all methods
//are encapsulated by this class scope.
public class Person {
	/*
	 * Any variables that are not within a method, but within a class is called
	 * instance variables. They are accessible throughout entire class
	 * and outside of them as well, depending access modifiers.
	 */
	String name;
	int age;
	
	/*
	 * A constructor is a method of a class that can be called first when 
	 * creating an instance of it. A constructor ALWAYS follows the format:
	 * AccessModifer ClassName(){}     <-- NOTE: No return type given.
	 */
	public Person(String name, int age){
		//'this' references the class itself.
		this.name = name;
		this.age = age;
	}
	public Person(){
		
	}
	/*
	 * Default Constructor:
	 * -When no constructor is given for a class, Java will supply a default
	 * constructor for the class to be used in cases of creating new instances.
	 * This default constructor has no arguments and simply calls the constructor for
	 * the Object class.
	 * 
	 * No args constructor:
	 * -Simply a constructor with no arguments.
	 */
}
