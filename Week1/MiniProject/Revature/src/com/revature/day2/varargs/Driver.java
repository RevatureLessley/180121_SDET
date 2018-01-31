package com.revature.day2.varargs;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * varargs is a var container that allows you to make the amount 
		 * of input a method can dynamic. In other words, if you use varargs
		 * you are saying that the method can take 0 - many inputs of that type.
		 */
	
	}
	
	
	public int sums(int ...nums){
		int result = 0;
		for (int i : nums){
			result +=i;
		}
		return result;
	}

}
