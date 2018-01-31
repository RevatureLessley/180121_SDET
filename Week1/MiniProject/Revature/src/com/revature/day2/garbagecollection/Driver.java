package com.revature.day2.garbagecollection;
/*
 * You are not allowed any where near management. Java will handle everything related to cleaning memory for u.
 * Any object will be cleaned up if any of the follow occurs:
 * 
 *-Reference points to null
 *-Mehtod that created object ends
 *-Reference is re assigned to a diff obj
 */
public class Driver {

	public static void main(String[] args) {
	
		for (int i = 0; i < 1000000; i++){
			Something s = new Something(i);
		}
	}

	/*
	 * Java collects objects in a seemingly random order
	 * 
	 */
}
