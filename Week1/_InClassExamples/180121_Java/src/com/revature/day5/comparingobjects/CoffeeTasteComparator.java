package com.revature.day5.comparingobjects;

import java.util.Comparator;

public class CoffeeTasteComparator implements Comparator<Coffee>{

	/*
	 * Comparators are an outside class that can be used to order an object in
	 * different ways other than natural ordering.
	 * As such, you need to implement a compare() method that comares two external
	 * instances of the class together.
	 */
	@Override
	public int compare(Coffee c1, Coffee c2) {
		return c1.getTaste().compareTo(c2.getTaste());
	}

}
