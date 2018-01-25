package com.revature.day1.scopes;

//Every class starts with the classname, all methods
//are encapsulated by this class scope
public class Person {
	/*
	 * Static makes a variable/method avialable as soon as the aplication is started.
	 * This means we do not need to create an instance  of a class to access any static means.
	 * On top of this, there can only be ONE instance of a static entity. Therefore the
	 * static int personCoun is shared by all instances of Person.
	 */
	static int personCount = 0;
	
	
	/*
	 * Any variables that are not within a method, but within a class is called
	 * instance variables. They are accessible throughout entire class
	 * and outside of them as well, depending access modifiers
	 */

	String name;
	int age;
	
	/*
	 * A constructor is a method of a class that can be called first when
	 * creating an instance of it.A constructor ALWAYS follows the format:
	 * AccessModifer ClassName(){} <---- NOTE: No return type given
	 */
	public Person () {
		incrementPersonCount();
	}
	
	public Person(String name, int age) {
		//'this' refefrences the class itself
		this.name = name;
		this.age = age;
		incrementPersonCount();
	}
	
	/*
	 * Default Constructor:
	 * -When no constructor is given for a class, java will supply a default
	 * constructor for the class to be used in cases of creating new instances.
	 * This default constructor has no arguments and simply calls the constrctor for
	 * the Object class.
	 * 
	 * No args constructor:
	 * -Simply a constructor with no arguments.
	 */
	
	public void someMethod() {
		//All of these variables are in 'Method' scope.
		/*
		 * Once the method is called, the vairables are created.
		 * The variables are then immediately available for garbage collection
		 * once the method ends execution;
		 * 
		 * Note: By garbage collection, I mean freeing up memory.
		 */
		
		int i = 5;
		double d = 5.00;
		float f = 5.0f;
		long l = 5;
		byte b = 5;
		short s = 5;
		char c = 65;
		boolean bool = true;
		
		/*
		 * You CAN create duplicate names of instance variables in the method scope,
		 * just be aware, in order to access the instance scope version' you will need
		 * the 'this' keyword
		 */
		String name = "whatever";
		System.out.println(name);
		
		
		/*
		 * J in this case is a loop scope variable.
		 * Once the loop ends, the variable j is a candidate for garbage collection.
		 */
		for (int j = 0; j < 10; j++) {
				System.out.println(j);
		}
	}
	/*
	 * Method for incrementing the static variable personCount any time
	 * a new instance of person is created.
	 */
	private void incrementPersonCount() {
		personCount++;
	
}
	/*
	 * The finalize method is the last method called on any object before it is
	 * collected for garbage colelction.
	 */
	@Override
	protected void finalize() throws Throwable {
	personCount--;
	}
}
