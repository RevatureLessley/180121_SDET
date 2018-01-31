package com.revature.day5.comparingobjects;

import java.util.Comparator;

public class CoffeeTasteComparator implements Comparator<Coffee>{

	@Override
	public int compare(Coffee c1, Coffee c2) {
		return c1.getTast().compareTo(c2.getTast());
	}
	
	

}
