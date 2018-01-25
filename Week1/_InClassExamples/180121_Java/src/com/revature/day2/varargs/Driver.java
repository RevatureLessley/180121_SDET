package com.revature.day2.varargs;

public class Driver {

	public static void main(String[] args) {
		/*
		 * varargs is a variable container that allows you to 
		 * make the amount of input a method can have dynamic.
		 * In other words, if you use varargs, you are saying that the method
		 * can take 0 - many inputs of that type.
		 */
		Driver d = new Driver();
		//System.out.println(d.sum(1));
		//System.out.println(d.sum(1,2));
		//System.out.println(d.sum(1,2,3));
		//System.out.println(d.sum(1,2,3,4));
		System.out.println(d.sum(1,2,3,4,5,6,7,5,4,3,2));
	}
	
	/*public int sum(int a){
		return a;
	}
	public int sum(int a, int b){
		return a + b;
	}
	public int sum(int a, int b, int c){
		return a + b + c;
	}*/
	//... denotes a vararg that can take 0 - many arguments. Note: nums is an array.
	public int sum(int ... nums ){
		int result = 0;
		for(int i : nums){
			result += i;
		}
		return result;
	}
}
