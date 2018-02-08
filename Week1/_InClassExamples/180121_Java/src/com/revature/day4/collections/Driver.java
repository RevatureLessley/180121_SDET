package com.revature.day4.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;

import com.revature.day3.serialization.Person;

public class Driver {

	public static void main(String[] args) {
		/*
		 * collection: A term for datastructures
		 * Collection: The API for java collections
		 * Collections: Utility class with methods for interacting with collections
		 */
		
		//Note: collections only deal with objects, therefor any primitive values you insert
		//are autoboxed.
		System.out.println("=======LISTS=======");
		ArrayList l1 = new ArrayList();
		l1.add("Bobbert");
		l1.add(17);
		l1.add(true);
		System.out.println(l1);
		
		/*
		 * Generics
		 * -enforcing a type for a collection.
		 * -In addition to making a stronger collection you can also use generics
		 * to dynamically type a collection at runtime. (For example using <?>)
		 */
		List<Integer> l2 = new ArrayList<>(); 	//Note, you can leave the second set of brackets
												//blank. Java assumes they match first set.
												//You CAN fill them in though.
		l2.add(5);
		l2.add(2);
		l2.add(7);
		l2.add(3);
		l2.add(1);
		l2.remove(4);
		System.out.println(l2);
		//l2.remove(17);//what happens here? IndexOutOfBoundsException
		System.out.println(l2.get(3)); //Which grabs the element at that index
		/*
		 * Methods of note:
		 * -add
		 * -remove
		 * -get
		 * -set
		 * -listIterator
		 */
		
		/*
		 * Since all collections in Collection subclasses Iterable, we can use a forEach loop
		 * to iterate through it.
		 */
		
		for(int i : l2){
			System.out.println(i);
		}
		
		/*
		 * Iterable offers us iterators, which are interactable cursors for a collection
		 */
		
		Iterator it1 = l2.iterator();
		System.out.println("====Iterators=====");
		System.out.println(it1.next());
		System.out.println(it1.next());
		while(it1.hasNext()){
			System.out.println(it1.next());
		}
		//resetting an iterator
		it1 = l2.iterator();
		
		System.out.println("=====ListIterator=====");
		//List offers us, specifically, a special iterator. The ListIterator
		ListIterator li = l2.listIterator();
		System.out.println(li.next());
		System.out.println(li.next());
		System.out.println(li.previous());
		System.out.println(li.previous());
		//System.out.println(li.previous()); NoSuchElementException
		
		
		System.out.println("======LinkedList=====");
		List ll = new LinkedList();
		Queue ll2 = new LinkedList();
		
		LinkedList ll3 = new LinkedList();
		ll3.add(4);
		ll3.add(5);
		ll3.add(6);
		System.out.println(ll3.indexOf(5));
		
		
		System.out.println("=====SETS=====");
		
		Set<Integer> set = new HashSet();
		set.add(7);
		set.add(1);
		set.add(4);
		set.add(123456789);
		set.add(2);
		for(Integer i : set){
			System.out.println(System.identityHashCode(i));
		}
		System.out.println(set);
		set.add(2);	//No duplicates, 
		System.out.println(set);
		
		for(Integer i : set){
			System.out.println(System.identityHashCode(i));
		}
		/*
		 * -Sets dont take duplicates.
		 * -You can add and remove from a set.
		 * -You can not randomly access a set.
		 * Notable Methods:
		 * -add
		 * -remove
		 * -iterator
		 */
		
		Set ex = new HashSet();
		Person p2 = new Person("adam","adamson",10,"dog");
		Person p1 = new Person("bob","bobby",123,"stuff");
		Person p3 = new Person("adam","adamson",10,"dog");
		
		ex.add(p2);
		ex.add(p1);
		ex.add(p3);
		for(Person p : (Set<Person>)ex){
			System.out.println(p);
		}

		
		
		
		
		System.out.println("======QUEUE=====");
		Queue<Integer> q = new LinkedList<>();
		q.offer(5);
		q.add(10);
		q.add(2);
		q.add(1);
		q.add(7);
		q.add(10);
		System.out.println("size: " + q.size());
		System.out.println(q.poll()); //Removes the front of the line
		System.out.println("size: " + q.size());
		
		int size = q.size();
		for(int i = 0; i <= size; i++){
			System.out.println(q.poll());
		}
		
		SynchronousQueue<Integer> cap = new SynchronousQueue<>();
		System.out.println(cap.offer(4));
		
		
		//System.out.println(q.remove()); NoSuchElementException on empty queues
		/*
		 * If you use add() on a full queue, it throws an IllegalStateException
		 * Whereas the offer() method will not, it will just refuse to add the element.
		 * (Returns false)
		 */
		
	}

}
