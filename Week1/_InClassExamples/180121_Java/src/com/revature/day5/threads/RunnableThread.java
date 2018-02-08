package com.revature.day5.threads;

/*
 * There are two different ways to create a Thread in java.
 * A thread is simple a flow of execution.
 * Multithreading is when multiple threads have branched off the main thread
 * and execute concurrently.
 * 
 * The goal of multithreading would be share the work load among multiple threads
 * in an effort to finish tasks faster.
 * 
 * With threads, Java has two ways to create Thread. These two ways include:
 * -IMPLEMENTING the Runnable INTERFACE
 * -EXTENDING the Thread CLASS
 */

public class RunnableThread implements Runnable{

	/*
	 * In either case for creating thread, you must implement the run()
	 * method. This method contains the tasks your separate thread will run when
	 * sent off on its merry way.
	 */
	@Override
	public void run() {
		for(int i = 0; i < 20; i++){
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
