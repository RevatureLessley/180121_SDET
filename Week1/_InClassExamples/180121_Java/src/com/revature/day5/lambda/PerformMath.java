package com.revature.day5.lambda;

/*
 * Functional Interfaces
 * -A functional interface is simply an interface with ONE method.
 * The goal is to implement them at runtime.
 * Having any method/class implemented at runtime is called an anonymous method/class.
 * It is called this because the implementation has no name to reference by. It is implemented 
 * AND executed on the spot.
 */

public interface PerformMath {
	public int calculate(int a, int b);
}
