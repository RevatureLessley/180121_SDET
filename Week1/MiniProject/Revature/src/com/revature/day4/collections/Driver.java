package com.revature.day4.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Driver {
	/*
	 * collection: A term for data structures Collection: The API for java
	 * collections Collections: Utility class with methods for interacting with
	 * collections.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("===========================Lists==============================");

		/*
		 * Generics: enforcing a type for a collection. In addition to making a
		 * stronger collection you can also use generics to dynamically type a
		 * collection at runtime. Ex: <?>
		 */

		List<Integer> l2 = new ArrayList<>();
		l2.add(5);
		l2.add(2);
		l2.add(6);
		l2.add(9);
		l2.add(1);
		
		l2.remove(4); 
		//l2.remove(17); //IndexOutofBound Exception
		System.out.println(l2);
		System.out.println(l2.get(3)); // gets the element at that index
		
		/*
		 * -add
		 * -remove
		 * -get
		 * -set
		 * -listIterator
		 * 
		 * Since all collections in Collection subclasses Iterable, we can use a forEach loop to iterate through it.
		 * 
		 */
		
		for(int i : l2){
			System.out.println(i);
		}
		
		Iterator it = l2.iterator();
		System.out.println("==========================Iterators==========================");
		System.out.println(it.next());
		
		// resetting an iterator
		it = l2.iterator();
		
		ListIterator li = l2.listIterator();
		System.out.println(li.next());
		System.out.println(li.next());
		System.out.println(li.previous());
		
		
		
		System.out.println("===============================SETS===============================");
		Set<Integer> set = new HashSet();
		set.add(7);
		set.add(4);
		set.add(9);
		set.add(10);
		set.add(2);
		System.out.println(set);
		set.add(2);					//No duplicates
		System.out.println(set);
		
		/*
		 * Sets dont take duplicates.
		 * -You can add and remove from a set.
		 * -Cant randomly access it.
		 * 
		 * Methods
		 * -add
		 * -remove
		 * -iterator
		 */
		
		System.out.println("=======================================Queue============================");
		Queue <Integer>que = new LinkedList<>();
		que.add(5);
		que.add(10);
		que.add(2);
		que.add(1);
		que.add(7);
		que.add(10);
		
		System.out.println(que.size());
		System.out.println(que.poll()); // Removes the front of the line
		System.out.println(que.size());
		
		Map <Integer, String> map = new HashMap<>();
		map.put(1, "Boobert");
		map.put(2, "Parth");
		map.put(3, "Iguana");
		
		map.remove(3);
		
	    System.out.println(map);
	    map.put(3, "Octupus");
	    map.put(4, "Octupus");
	    
	    System.out.println("====================HashTable===================================");
	    Map<Integer,String> table = new Hashtable<>();
	    table.put(1, "Boobert");
		table.put(2, "Parth");
		table.put(3, "Iguana");
		table.put(4, "Giraffe");
		
		System.out.println(table);
		/*
		 * Hashtables do not accept null keys, hashmaps do.
		 * -HAshMaps are not synchnorized, hashtables are.
		 * -Hashtables are considered a legacy class.
		 * 		-Legacy classes are classes that are from older versions of java that have proper replacements
		 * 		(IE hashmap in this case) but they can still have use, and therefore are not a candidate for deprication
		 * 		(In this case, hashtables are thread safe).
		 * 			- Deprecated means the method has a full replacement and should not be use. 
		 */
		
		
	}

}
