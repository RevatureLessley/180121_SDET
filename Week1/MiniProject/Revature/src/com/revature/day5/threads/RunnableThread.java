package com.revature.day5.threads;

/*
 * Thre are two different ways to create a thread in java. A thread is a simple flow
 * of execution. Multithreading is when multiple threads have branched off the main thread
 * and execute concurrently. 
 * 
 * The goal of multithreading would be to share the work load among multiple threads 
 * in an effort to finish tasks faster. 
 * 
 * With threads java has 2 ways to create a thread. 
 * -Implementing the runnable interface
 * -Extending the Thread class
 */
public class RunnableThread implements Runnable {

	/*
	 * In either case for creating a thread, you must implement the run() method. 
	 * This method contains the task your sperate thread will run when sent off in its merry way.
	 */
	@Override
	public void run() {
		
		for(int i =9; i < 20; i++){
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
