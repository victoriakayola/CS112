/* 
 * Purpose: This program performs calculations for integer arrays.
 */
package hw1;

import java.util.Scanner;

public class ArrayStatistics {

	public static double getMin(double[] numbers) {
		double min = numbers[0];

		for (double n : numbers) {
			if (n < min)
				min = n;
		}
		return min;
	}

	public static double getMax(double[] numbers) {
		double max = numbers[0];

		for (double n : numbers) {
			if (n > max)
				max = n;
		}
		return max;
	}

	public static double getSum(double[] numbers) {
		double sum = 0;
		for (double n : numbers) {
			sum += n;
		}
		return sum;
	}

	public static void main(String[] args) {

		System.out.println("\nWelcome to the Array Statistics Program!");

		// Define a scanner for user input
		Scanner userInput = new Scanner(System.in);

		// Ask for the number of doubles to input
		System.out.println("\nHow many doubles would you like to input?");
		int n = userInput.nextInt();

		double[] numbers = new double[n];

		for (int i = 0; i < n; ++i) {
			System.out.println("Please input a double:");
			numbers[i] = userInput.nextDouble();
		}

		/*
		 * Call the three methods (getSum, getMax, getMin) here.
		 */
		double sum = getSum(numbers);
		double max = getMax(numbers);
		double min = getMin(numbers);

		System.out.println("\nThe average is: " + (sum / n));
		System.out.println("The maximum is: " + max);
		System.out.println("The minimum is: " + min);

		userInput.close();

	}

}
