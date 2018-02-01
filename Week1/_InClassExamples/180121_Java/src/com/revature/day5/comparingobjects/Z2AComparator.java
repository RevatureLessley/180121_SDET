package com.revature.day5.comparingobjects;

import java.util.Comparator;

public class Z2AComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		return (o1.compareTo(o2))*-1;
	}

}
