package com.revature.day5.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Driver {
	/*
	 * Lambdas, as introduced in Java 8, allows us to implement functional
	 * interfaces on the spot. It does this using arrow notation.
	 */
	
	/*
	 * In the above example, we use the interface "PerformMath" to provide us
	 * with the method we are implementing. In this case the method was called calculate.
	 * The arrow notation shows us the following:
	 * (Variables to be used) -> (Expression that can be used to return something)
	 * The signature provided in the interface had a return type of int, and had parameters of
	 * int. Using that info, we don't to provide it in the implementation above.
	 * 
	 * Note: Syntax quirks include:
	 * If the functional interface method, has no parameters.
	 * Lambda MUST look like this:
	 * () -> stuff
	 * If the interface takes ONE parameter, both the following are valid:
	 * (var) -> stuff
	 * var -> stuff
	 * If there are more than 1 parameters, ONLY the following is valid
	 * (var1, var2, ...varn) -> stuff
	 * 
	 */
	
	public static void main(String[] args) {
		PerformMath addition = (a, b) -> (a + b);
		PerformMath subtraction = (a,b) -> (a - b);
		Message message = msg -> {
		
		
		System.out.println(addition.calculate(2,5));
		
		System.out.println(subtraction.calculate(5, 2));
			
			System.out.println("Hello " + msg); 
		
		};
		
		message.printMessage("Stuff");
		
		List<Integer> ints = Arrays.asList(7,2,55,1,0,9,22,17,12);
		
		for(Integer i : ints){
			System.out.println(i);
		}
		
		/*
		 * Method references
		 * -A shorthand call to the method of a class on the spot.
		 * -In order to invoke a method reference the following syntax must be used:
		 * 
		 * Class::method
		 * Class::new <- This will instantiate an object every time
		 */
		System.out.println("==========");
		ints.forEach(System.out::println);

		/*
		 * In addition to lambdas, java 8 offers us predicates and streams.
		 * Streams lets the data of a collection sent as stream of data to some consumer.
		 * This allows to dynamically alter any list on the spot, as well as implement
		 * anonymous filters.
		 * A predicate, which stores the runtime implementation for filtering out data,
		 * is simply a functional interface with a single boolean method for determining
		 * what is allowed to be displayed.
		 * 
		 */
		
		System.out.println(ints.stream().sorted().collect(Collectors.toList()));
		/*
		 * A breakdown of above looks like this:
		 * We take our collection of integers: ints
		 * and we convert it to a stream of data via .stream().
		 * We essentially put it through a for each loop mid line.
		 * Once you have your stream started, you start to manipulate each value
		 * individually.
		 * In this case we call "sorted()", which goes through the stream and 
		 * sorts each value by it's natural order.
		 * Once done, we call collect, which as the name states, collects all the data
		 * from the stream and encapsulates it as another datatype, in this case, list.
		 */
		
		System.out.println(ints.stream().sorted().filter(x -> x%2==1).collect(Collectors.toList()));
		System.out.println(ints.stream().filter(customPredicate()).collect(Collectors.toList()));
		
		
	}
	/*
	 * In order to make a custom predicate to be used for filters,
	 * You first need to make a function returns a predicate.
	 * -To return a predicate you simply need to return a lambda evaluation
	 * that evaluates to either true or false
	 * So below I make a custom predicate that will filter out any Integers that
	 * are 10 or less, or 30 or more.
	 */
	public static Predicate<Integer> customPredicate(){
		return p -> p>10 && p < 30;
	}
}
