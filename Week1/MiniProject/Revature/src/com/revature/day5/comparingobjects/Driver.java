package com.revature.day5.comparingobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<Coffee> l1 = Arrays.asList(
				new Coffee("Foldger", "Incredible", 2),
				new Coffee("Bustelo", "Strong", 1),
				new Coffee("Pillon", "Robust", 3)
				);
		
		System.out.println("======================Before Sort==================");
		
		for(Coffee c : l1){
			System.out.println(c);
		}
		l1.sort(null);
		
		System.out.println("================After Sort======================");
		for(Coffee c : l1){
		System.out.println(c);
		}
	}

}
