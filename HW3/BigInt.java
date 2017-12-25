/*
 * Purpose: static library class for big integers that go outside of the range allowed by Java(driver not included)
 */
package hw3;

import java.util.Arrays; 

public class BigInt {

	public static final int SIZE = 20; // length of arrays representing big ints

	public static final int[] NaBI = { -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; // error value

	// Convert the integer n into a big integer
	public static int[] intToBigInt(int n) {
		int[] arr = new int[BigInt.SIZE];

		if (n < 0) {
			return NaBI;
		}

		else if (n == 0) {
			arr[arr.length - 1] = 0; // making the last index in the bigInt a significant digit, not a leading 0

		}

		else {
			for (int i = arr.length - 1; i >= 0; i--) {
				arr[i] = n % 10; // using modulus will put the remainder (last digit) into the array
				n /= 10; // updating n so next time I can use modulus and get the remainder again

			}

		}
		return arr;
	}

	// Convert the String s into a big integer
	public static int[] stringToBigInt(String s) {
		int[] arr = new int[BigInt.SIZE];

		if (s.length() > SIZE)
			return NaBI;

		boolean isNotBigInt = false;

		for (int i = 0; i < s.length(); i++) { // this loop checks if anything in the array is anything other than a
												// digit

			if (!Character.isDigit(s.charAt(i))) {

				isNotBigInt = true;
			}
		}
		if (isNotBigInt) {
			return NaBI;
		}

		int j = s.length() - 1;
		for (int i = arr.length - 1; i >= 0; i--) {

			while (j >= 0) {
				arr[i] = Character.getNumericValue(s.charAt(j)); // adding the digit at index i to arr
				j--;
				break;
			}
		}

		return arr;
	}

	// Return a String representation of the big integer
	public static String bigIntToString(int[] A) {
		String stringRepresentation = "";
		for (int e : A) {
			if (e > 9 || e < 0)
				return "NaBI";
		}
		boolean allZeroes = true;
		for (int e : A) {
			if (e != 0) {
				allZeroes = false;
			}

		}
		if (allZeroes) { // if everything is a zero, just prints out one zero and not SIZE zeros
			return "" + 0;
		}

		for (int e : A) {
			if (e == 0 && stringRepresentation.length() == 0) { // makes sure it doesn't print out leading zeros but
																// prints other zeros

				continue; // don't do anything just keep going
			} else
				stringRepresentation += "" + e;
		}

		return stringRepresentation;
	}

	// Compare the two big integers A and B and return -1, 0, or 1, depending
	// on whether A < B, A == B, or A > B, respectively.
	public static int compareTo(int[] A, int[] B) {

		boolean bGreater = false;
		boolean aGreater = false;

		for (int i = 0; i < A.length; i++) {
			if (A[i] < B[i]) {
				bGreater = true;
			}
			if (A[i] > B[i]) {
				aGreater = true;
			}

		}

		if (bGreater) {
			return -1;
		}
		if (aGreater) {
			return 1;
		}

		return 0; // return 0 if neither bGreater or aGreater are true (they are equal)
	}

	// Add two big integers and return a new array representing their sum; if the
	// result overflows,
	// i.e., contains more than SIZE digits, return NaBI.

	public static int[] add(int[] A, int[] B) {
		int[] arr = new int[BigInt.SIZE];

		for (int i = A.length - 1; i > 0; i--) {

			int tempSum = A[i] + B[i];

			if (tempSum >= 10) {
				arr[i] = tempSum % 10; // putting just the last digit in so that the other will carry
				A[i - 1] += tempSum / 10; // adding the carry to the previous index

			} else
				arr[i] = tempSum;
		}

		arr[0] = A[0] + B[0]; // adding the first terms outside of the loop because now all the original array
								// values are updated to reflect the carry
		if (arr[0] >= 10) {
			return NaBI;
		}

		return arr;

	}

}
