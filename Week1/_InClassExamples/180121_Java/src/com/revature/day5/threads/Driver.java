package com.revature.day5.threads;

public class Driver {
	public static void main(String[] args) {
		/*
		 * With Threads, you create a Thread instance, and pass it our
		 * instance of a class implementing Runnable.
		 * Optionally, you can give it a name as well.
		 * When you finally want to fork the thread on it's own path,
		 * you will use the start() method! <-- IMPORTANT
		 */
		Thread t1 = new Thread(new RunnableThread(), "Thread1");
		Thread t2 = new Thread(new ThreadThread(), "Thread2");
		System.out.println("Beginning Execution");
		t1.start();
		t2.start();
		System.out.println("Ending Execution");
	}
}
