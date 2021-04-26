package javaDemo;

public class FibonacciSeries {

	public static void main(String[] args) 
	{
		int n1=0, n2=1, n3, count =10;
		System.out.println("\n*** Printing the Fibonacci series ***\n");
		System.out.print(n1 + "  " + n2);
		for (int i = 2; i <= count; i++)
		{
			n3 = n2 + n1;
			System.out.print("  "+ n3);
			n1 = n2;
			n2 = n3;
		}

	}

}
