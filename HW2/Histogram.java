/* 
 * Purpose: This program prints out a histogram of the numbers input by the user 
 */
package hw2;

import java.util.Scanner;

public class Histogram {
	public static final int MAX_NUMBERS = 20; // maximum number of tempArr to input
	public static final int NUM_BINS = 10; // number of bins in range [0..100]
	public static final int BIN_SIZE = 100 / NUM_BINS;

	public static void printHeading() {
		System.out.println("Welcome to the Histogram Program!\n");
	}

	public static void printHistogram(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			if (i == 0)
				System.out.print("\n[" + i + ".." + (i + BIN_SIZE) + "]: \t");
			else
				System.out.print("\n(" + (i * BIN_SIZE) + ".." + (i * BIN_SIZE + BIN_SIZE) + "]: \t");
			for (int j = 0; j < arr[i]; j++) {
				System.out.print("*");
			}
		}

	}

	public static boolean validInput(double input) {
		if (input >= 0 && input <= 100)
			return true;
		return false;
	}

	public static void main(String[] args) {

		double[] nums = new double[20];

		Histogram.printHeading();

		Scanner userInput = new Scanner(System.in);
		System.out.println(
				"This program will print out a histogram of the numbers input by the user;\nenter up to 20 doubles in the range [0 .. 100]; enter -1 to end. ");

		double[] tempArr = new double[MAX_NUMBERS];
		int arrayPlace = 0;
		int numInputs = 0;
		while (userInput.hasNextDouble()) {

			double input = userInput.nextDouble();

			numInputs++;

			if (input == -1)
				break;
			if (!Histogram.validInput(input))
				System.out.println("Input must be a double in range [0..100], try again!");
			else if (numInputs > MAX_NUMBERS) {
				System.out.println("Maximum number of inputs (" + MAX_NUMBERS
						+ ") exceeded, proceeding to calculate Histogram... \n");
				break;
			} else {
				tempArr[arrayPlace] = input;
				arrayPlace++;
			}

		}

		double[] newArr = new double[arrayPlace];

		// copying values from tempArr to newArr
		for (int i = 0; i < newArr.length; i++) {
			newArr[i] = tempArr[i];
		}

		System.out.print("\nYou input " + newArr.length + " numbers: [");

		for (int i = 0; i < newArr.length; i++) {
			if (i == newArr.length - 1)
				System.out.print(newArr[i] + " ]");
			else
				System.out.print(newArr[i] + ", ");
		}

		System.out.println("\nHistogram of Values in Decades from 0 to 100:");
		int[] histogram = new int[NUM_BINS];

		// this fills up the bins
		for (int i = 0; i < newArr.length; i++) {
			if (newArr[i] == 0)
				histogram[0]++;
			for (int j = 0; j < NUM_BINS; j++) {

				if (newArr[i] > j && newArr[i] <= (j + 1) * BIN_SIZE) {
					histogram[j]++;
					break;
				}

			} 
		} 

		Histogram.printHistogram(histogram);

	}
}
