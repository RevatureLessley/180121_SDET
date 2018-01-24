package com.revature.day2.finalexample;

/*
 * The final keyword can be used to limit various entities of a class.
 * If behaves a little differently whether attached to a class, method, or variable.
 */
public class FinalClass {
	//Value of WORD can never be changed.
	private final String WORD = "Something indeed";
	
	/*
	 * Method can not be overridden.
	 * Method CAN be overloaded
	 * Though the method cannot be changed, it still can be inherited.
	 */
	public final void something(){
		System.out.println(WORD);
	}
}
