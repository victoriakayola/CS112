/* 
 * Purpose: This program calculates various statistics for three inputs.
 */
package hw1;

import java.util.Scanner;

public class Statistics {



	public static int computeSum(int n1, int n2, int n3) {
		return n1 + n2 + n3;
	}

	public static int getMax(int n1, int n2, int n3) {
		return Math.max(Math.max(n1, n2), n3);
	}

	public static int getRange(int n1, int n2, int n3) {
		return getMax(n1, n2, n3) - Math.min(Math.min(n1, n2), n3);
	}

	public static double getAvg(int n1, int n2, int n3) {
		return computeSum(n1, n2, n3) / 3.0;
	}

	public static double getStandardDeviation(int n1, int n2, int n3) {
		double avg = getAvg(n1, n2, n3);
		double sqrDif = Math.pow(avg - n1, 2) + Math.pow(avg - n2, 2) + Math.pow(avg - n3, 2);
		return Math.sqrt(sqrDif / 3.0);

	}

	public static String ascendOrder(int n1, int n2, int n3) {
		int min = Math.min(Math.min(n1, n2), n3);
		int max = getMax(n1, n2, n3);
		int median = computeSum(n1, n2, n3) - (max + min);

		return "" + min + " " + median + " " + max;
	}	

	public static void main(String[] args) {

		Scanner userInput = new Scanner(System.in);

		System.out.println(
				"\nWelcome to the Statistics Program!\nThis program will print out the sum, max, range, mean, standard d		eviation, and\nand ordered list for the three integeres input by the user.");

		System.out.println("\nType in the first number and hit return: ");
		int numOne = userInput.nextInt();

		System.out.println("\nType in the second number and hit return: ");
		int numTwo = userInput.nextInt();

		System.out.println("\nType in the third number and hit return: ");
		int numThree = userInput.nextInt();

		System.out.println("\nYou have input the numbers " + numOne + ", " + numTwo + ", and " + numThree + '.');

		System.out.println("\nThe sum is " + computeSum(numOne, numTwo, numThree));
		System.out.println("The max is " + getMax(numOne, numTwo, numThree));
		System.out.println("The range is " + getRange(numOne, numTwo, numThree));
		System.out.printf("The mean is %.4f\n", getAvg(numOne, numTwo, numThree));
		System.out.printf("The standard deviation is %.4f\n", getStandardDeviation(numOne, numTwo, numThree));
		System.out.println("The three numbers in order are: " + ascendOrder(numOne, numTwo, numThree));

	}

}
