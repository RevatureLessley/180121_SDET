package com.revature.day2.finalExample;

/*
 * The final keyword can be used to limit various entities of a class.
 * it behave a little differently whether attached to a class, method, or variable.
 */
public class FinalClass {
	//Value of WORD can never be changed
	private final String WORD = "String indeed";
	//Method can NOT be overrriden.
	//Method CAN be overwritten.
	//Though the method can NOT be changed, it CAN be inherited.
	public final void something() {
		System.out.println(WORD);
	}
}
