package driver;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
	      int i = 4;
	      int ia[][][] = new int[i][i = 3][i];
	      System.out.println( ia.length + ", " + ia[0].length+", "+ ia[0][0].length);
	      
	        List s1 = new ArrayList( );         try{             while(true){                 s1.add("sdfa");             }         }catch(RuntimeException e){             e.printStackTrace();         }         System.out.println(s1.size());
	}
	
	  public static void printSum(int a, int b){       System.out.println("In int "+(a+b));   }      public static void printSum(Integer a, Integer b){       System.out.println("In Integer "+(a+b));   }

	  public static void printSum(double a, double b){       System.out.println("In double "+(a+b));   }
}
