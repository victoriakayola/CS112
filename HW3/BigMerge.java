/*
 * Purpose: Helper methods for a program that will take an array of sorted arrays and merge them into one (driver not included)
 */

package hw3;

public class BigMerge {

	/*
	 * Returns j such that a[j][index[j]] is the minimum of the set
	 * S={a[i][index[i]] for every i such that index[i]<a[i].length} If the set S is
	 * empty, returns -1 Runs in time a.length.
	 * 
	 * NOTE: normally this would be private, but leave it public so we can test it
	 * separately from your merge.
	 */
	public static int argMin(int[][] a, int[] index) {

		boolean allUsed = true;

		for (int i = 0; i < index.length; i++) { // checks to see if all the values have already been returned to merge
			if (index[i] != a[i].length) {
				allUsed = false;
			}
		}

		if (allUsed) {
			return -1;
		}

		int j = 0;
		for (int i = 0; i < a.length; i++) {
			if (index[i] < a[i].length) { // initializes j as the first index that hasn't been used yet
				j = i;
				break;
			}
		}

		for (int i = 0; i < a.length; i++) {
			if (index[i] < a[i].length) { // makes sure that the array at a[i] has not returned all its indices to merge
											// yet

				if (a[i][index[i]] < a[j][index[j]]) { // compares the value at current index to value at j, which is
														// the current smallest
					j = i; // updates j if the value at i is smaller than the previous smallest

				}
			}
		}

		return j;
	}

	/*
	 * Assumes a[0]...a[length-1] each is sorted in increasing order Returns a
	 * single sorted array containing all the elements in a Does not modify a. Runs
	 * in time k^2 * n, where k is a.length and n is the total number of integers in
	 * a.
	 */
	public static int[] merge(int[][] a) {

		int retSize = 0;

		for (int i = 0; i < a.length; i++) { // finding the length of the new merged array
			retSize += a[i].length;
		}

		int[] ret = new int[retSize]; // new array that will hold all the sorted values from a
		int[] index = new int[a.length];

		for (int i = 0; i < ret.length; i++) {
			int nextSmallest = argMin(a, index);

			if (nextSmallest == -1) {
				break;
			}

			ret[i] = a[nextSmallest][index[nextSmallest]]; // adds new smallest value to ret
			index[nextSmallest]++; // adds one to the appropriate index of the index array

		}

		return ret;
	}
}
