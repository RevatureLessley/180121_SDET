package com.revature.day5.comparingobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		//Arrays utility class
		List<Coffee> l1 = Arrays.asList(
					new Coffee("Foldger", "Incredible", 2),
					new Coffee("Bustelo", "Reeeeally Strong, I think Ryan will love it", 3),
					new Coffee("Pillon", "Robust", 1)
				);
		
		System.out.println("=====BEFORE SORT=====");
		
		for(Coffee c : l1){
			System.out.println(c);
		}
		
		l1.sort(null); //Sort via natural ordering
		
	
		System.out.println("=====AFTER SORT=====");
		
		for(Coffee c : l1){
			System.out.println(c);
		}	
		
		
		System.out.println("=====AFTER TASTE SORT=====");
		l1.sort(new CoffeeTasteComparator()); //Sort via comparator
		
		for(Coffee c : l1){
			System.out.println(c);
		}	
		
		
		/*
		 * You can use the Collections utility class to perform numerous 
		 * Collection manipulation methods on any collection of your choice.
		 */
		
		
	}

}
