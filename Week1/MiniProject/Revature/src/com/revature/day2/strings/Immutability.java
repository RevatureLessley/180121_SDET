package com.revature.day2.strings;

public class Immutability {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * String class is one of the most important classes in java, as it
		 * provides us means to use human language throughout applications,
		 * conveniently.
		 * 
		 * String is essentially an array of chars. It is also a FINAL class. It
		 * is also Immutable, which means once a string is set, it cannot be
		 * changed ever! Immutable = Unchangeable.
		 */

		/*
		 * == vs .equals == is an operator and .equals is a method == will check
		 * to see if the object is exactly the same .equals compares the values
		 * of the objects together
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

		System.out.println("====================");
		s1 = new String("bobbert");
		s2 = new String("bobbert");
		System.out.println(s1 == s2);
		s1 = s1.intern();
		s2 = s2.intern();
		System.out.println(s1 == s2);

		System.out.println("========================");

		/*
		 * In situations where a striing will go through numerous manipulations,
		 * one can reach a point where you are dealing with huge overhead memory
		 * and consumption since you are creating a new string every time Java
		 * offers a mutable type of string that can work. StringBuilder and
		 * StringBuffer.
		 * 
		 * What is the difference between String, StringBuffer, String Builder?
		 * 
		 * -String is immutable, whereas buffer and builder are not. -Builder is
		 * threadsafe (not synchnorized) -Buffer is synchnorized Thread safe
		 * means only one thread can use it at a time
		 */

		StringBuilder sb = new StringBuilder("dogs");
		System.out.println("sb: " + System.identityHashCode(sb));
		sb.append("s");
		System.out.println("sb: " + System.identityHashCode(sb));

		s1 = "dog";
		System.out.println("s1: " + System.identityHashCode(s1));
		s1 += "s";
		System.out.println("s1: " + System.identityHashCode(s1));

		// Hash code is to identify uniqueness.

	}

}
