package com.revaure.day2.finalclass;

public class FinalClass {

	private final String WORD = "Something";
	
	/* 
	 * Method can not be over ridden
	 * Method CAN be overloaded
	 * Though the method cannot be changed, it still can be inherited
	 */
	public final void something(){
		System.out.println(WORD);
	}
}
