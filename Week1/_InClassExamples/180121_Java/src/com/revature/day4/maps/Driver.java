package com.revature.day4.maps;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class Driver {

	public static void main(String[] args) {
		/*
		 * Maps are a collection of key/value pairs.
		 * More specifically, a combination of a keyset and entry values.
		 * It is worth noting that maps are not a part of the Collection
		 * API.
		 */
		
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "Bobbert");
		map.put(2, "Ryan");
		map.put(3, "Iguana");
		map.put(4, "Giraffe");
		System.out.println(map);
		
		map.remove(3);
		System.out.println(map);
		
		map.put(4, "Octopus");
		map.put(3, "Bobbert");
		
		System.out.println(map);
		map.put(0, null);
		map.put(null, "Stuff");
		map.put(null, null);
		
		System.out.println(map);
		/*
		 * With maps, the keys MUST be unique. If you try to insert a key that already exists,
		 * you will overwrite the value of that key.
		 * There are no duplicate limitations for values however.
		 * In a HashMap, you may use null for a key ONCE, more times overwrites its value.
		 * You can have as many null values as you want, since in the end, Maps only take
		 * objects.
		 */
		
		System.out.println("======HashTable=====");
		
		Map<Integer, String> table = new Hashtable<>();
		

		table.put(3, "Iguana");
		table.put(4, "Giraffe");
		table.put(1, "Bobbert");
		table.put(2, "Ryan");
		
		System.out.println(table);
		
		//table.put(0, null); NPE
		//table.put(null, "Dogbert"); NPE
		//table.put(null, null); NPE
		
		/*
		 * HashTables
		 * -The key difference between HashTables and HashMaps is that HashTables do not
		 * accept null keys OR values.
		 * There are more differences.
		 * -HashMaps are not synchronized, Hashtables are.
		 * -Hashtables are considered a legacy class.
		 * 		-Legacy classes are classes that are from older version of java that have
		 * 		proper replacements (IE HashMap in this case) but they can still have use,
		 * 		and therefore are not a candidate for deprecation. (In this case, HashTables
		 * 		are threadsafe)
		 * 			-Deprecated means the method has a full replacement and should not be used.
		 */
		
		//Iterating through a map
		for(Integer i : map.keySet()){
			System.out.println(map.get(i));
		}
	}

}
