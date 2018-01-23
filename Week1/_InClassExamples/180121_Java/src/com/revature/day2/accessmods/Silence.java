package com.revature.day2.accessmods;

public class Silence {
	/*
	 * There are two kinds of 'modifiers' available in java that you can use.
	 * One of the two are called 'access modifiers' which there are four:
	 * -Private
	 * ---Restricts access to class entity from anywhere that is not the
	 * ---class itself. IE, you cannot access private meth0ds or variables
	 * ---from outside of this class.
	 * -default
	 * ---We refer to this modifier as default, when in reality it is just any variable
	 * ---that does NOT have an access modifier attached.
	 * ---Access to default is restricted to anything that is within the same package as the class.
	 * -Protected
	 * ---Protected offers access to any classes within the same package or any classes that
	 * ---are a subclass of the main class.
	 * -Public
	 * ---Accessible from everywhere.
	 * 
	 * The other subset can be called non-access modifiers:
	 * -static
	 * -final
	 * -abstract
	 * -native
	 * -strictfp
	 * -volatile
	 * -synchronized
	 * -transient
	 */
	
	private String priv = "Private";
	String def = "Default";
	protected String prot = "Protected";
	public String pub = "Public";
	
	public void outputVariables(){
		
		System.out.println(priv);
		System.out.println(def);
		System.out.println(prot);
		System.out.println(pub);
		
	}
}
