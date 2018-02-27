package com.revature.day5.threads;

/*
 * The other way of creating Thread is by extending the Thread class.
 * Since Thread also implements runnable, this class will transitively do so as
 * well.
 * 
 * So which one to use?
 * Most developers go the path of implementing runnable. The reason for this is that
 * by implementing runnable, we still ourselves option to extend another class.
 * This is opposed to extending thread where we are then blocked from extending 
 * anything else.
 * Arguably, if you extending a class, you are intending to override base methods for
 * better use, but typically, Thread is already built to be efficient, so chances are 
 * you will not be able to write a better implementation.
 */
public class ThreadThread extends Thread{

	@Override
	public void run() {
		for(int i = 0; i < 20; i++){
			System.out.println("\t\t" + Thread.currentThread().getName());
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
