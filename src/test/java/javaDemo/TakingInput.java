package javaDemo;

import java.util.Scanner;

public class TakingInput 
{
	public static void main(String args[])
	{
		String input = "";
		System.out.println("Enter a string input - ");
		Scanner sc = new Scanner(System.in);
		input = sc.nextLine();
		while(input.isEmpty() || input == null)
		{
			System.out.println("Please enter a vaild string, Empty & Null strings are not allowed");
			input = sc.nextLine();
		}
		sc.close();
		System.out.println("User has entered the following string ==> " + input);
	}
}
