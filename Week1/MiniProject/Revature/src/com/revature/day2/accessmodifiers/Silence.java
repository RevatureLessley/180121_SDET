package com.revature.day2.accessmodifiers;

public class Silence {

	/*
	 * There 2 kinds of modifiers available in java that you can use. One of the
	 * 2 are called 'access modifiers' which there are 4:
	 * 
	 * -Private --- REstricts access to a class entity from anywhere that is not
	 * the class itself. IE, \ you cannot access private methods or variables
	 * from outside of this class -default --- It is any variable that does not
	 * have an access modifier attached.
	 * 
	 * -Protected --- Offers access to any classes within the same package or
	 * any classes that a subclass of the main class.
	 * 
	 * -Public --- Accessible from everywhere.
	 * 
	 * The other subset can be called non-access modifiers -static ---- Sets
	 * resource to be available w/o instance of class, limits to only one
	 * instance of resource shared among all other instances of class
	 * 
	 * -final ---- limits scalability of resource
	 * 
	 * -abstract ---- foregoes body implementation is done outside of java
	 * 
	 * -native ---- no body for the method, implementation is done outside of
	 * java -strictfp ---- if on class, all methods are strictfp ---- all
	 * floating points conform to IEEE standards -volatile ---- makes accessible
	 * by unsynchnorized threads
	 * 
	 * -synchnorized ---- limits the amount of threads that can acess resource
	 * to one at a time
	 * 
	 * -transient --- anything transient is witheld from serialization
	 */
	private String priv = "private";

	String def = "default";

	public String pub = "Public";

	protected String prot = "Protected";

	public void outputVariables() {

		System.out.println(priv);
		System.out.println(def);
		System.out.println(prot);
		System.out.println(pub);

	}
}
