package com.revature.day2.strings;

public class Immutability {

	public static void main(String[] args) {
		/*
		 * The String class is one of the most important classes in Java, 
		 * as it provides us a means to use human language throughout applications,
		 * conveniently.
		 * 
		 * String is essentially an array of characters.
		 * String is also a FINAL class
		 * String is IMMUTABLE!
		 * -Once a string is set, it can not be changed ever!
		 * --Immutable simply means unchangeable
		 */
		
		String s1 = "dog";
		System.out.println(s1);
		s1 += "s";
		System.out.println(s1);
		
		String s2 = "dogs";
		
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		
		s1 = s1.intern();
		
		System.out.println(s1 == s2);
		/*
		 * == vs .equals
		 * 
		 * -First and foremost, == is an operator and .equals method.
		 * -== will check to see if the object is exactly the same.
		 * --It checks to see if it is the same exact reference for each comparison.
		 * -.equals compares the values of the objects together.
		 */
		
		System.out.println("============");
		s1 = new String("bobbert");
		s2 = new String("bobbert");
		System.out.println(s1==s2);
		s1 = s1.intern();
		s2 = s2.intern();
		System.out.println(s1==s2);
		
		System.out.println("============");
		/*
		 * In situations where a string will go through numerous manipulations, one can reach
		 * a point where you are dealing with huge overhead and memory consuption since you are
		 * creating a new string every time.
		 * Java offers a mutable type of String that you can work.
		 * StringBuilder and StringBuffer
		 * 
		 * What is the difference between String, StringBuffer, and StringBuilder?
		 * -String is immutable, whereas buffer and builder are not;
		 * -StringBuilder is not synchronized (Thread Safe) whereas buffer is synchronized.
		 * ---Thread safe means only one thread can use it at a time.
		 */
		
		StringBuilder sb = new StringBuilder("dogs");
		
		/*
		 * Hashcode is something all objects have in java. It serves as a code to identify
		 * uniqueness. Mostly used in hashing functions for collections.
		 * However it comes with added effect of psuedo-representing position in memory.
		 */
		System.out.println(sb + ": " + System.identityHashCode(sb));
		sb.append("s");
		System.out.println(sb + ": " + System.identityHashCode(sb));
		
		
		s1 = "dog";
		System.out.println("s1: " + System.identityHashCode(s1));
		s1 += "s";
		System.out.println("s1: " + System.identityHashCode(s1));
		
		
		System.out.println("========SPEED TEST=======");
		
		StringBuilder sbui = new StringBuilder("");
		StringBuffer sbuf = new StringBuffer("");
		String str = new String("");
		
		int SIZE = 100000000;
		System.out.println("Looping " + SIZE + " times!");
		long curtime;
		
/*		curtime = System.currentTimeMillis();
		for(int i = 0; i <SIZE; i++){
			str += "s";
		}
		System.out.println("STRING: " + (System.currentTimeMillis() - curtime))*/;
		
		curtime = System.currentTimeMillis();
		for(int i = 0; i <SIZE; i++){
			sbui.append("s");
		}
		System.out.println("STRING BUILDER: " + (System.currentTimeMillis() - curtime));
		
		curtime = System.currentTimeMillis();
		for(int i = 0; i <SIZE; i++){
			sbuf.append("s");
		}
		System.out.println("STRING BUFFER: " + (System.currentTimeMillis() - curtime));
		
	}

}
