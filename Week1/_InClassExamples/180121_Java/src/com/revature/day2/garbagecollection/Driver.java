package com.revature.day2.garbagecollection;

public class Driver {

	public static void main(String[] args) {
		/*
		 * Once again, in Java, you are not allowed anywhere near memory 
		 * management. This isn't C++! 
		 * Java will handle everything related to cleaning memory for you.
		 * Any object created will be cleaned up if any of the follow occurs:
		 * -Reference points to null
		 * ---Object o = new Object();
		 * ---o = null;
		 * -Method that created object ends
		 * ---method(){
		 * 		Object o = new Object()
		 * }
		 * <-- At this point, Object() can collected.
		 * -Reference is reassigned to a different object.
		 * ---Object o = new Object();
		 * 	o = new Object(); <-- at this point, other object gets collected.
		 */
		
		for(int i = 0; i < 1000000; i++){
			Something s = new Something(i);
			System.gc();
		}
		/*
		 * Java collects objects in a seemingly random order
		 * It typically is a bad idea to REQUEST garbage collection via
		 * System.gc because it significantly slows down the program.
		 */
	}

}
