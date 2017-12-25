/* 
 * Purpose: This program returns the floor of the square root of the input.
 */
package hw1;

public class SquareRoot {

	public static int squareRoot(int n) {
		if (n == 0 || n == 1) {
			return n;
		}

		int low = 0;
		int high = n;

		while (low <= high) {
			int mid = (low + high) / 2;
			int sqr = mid * mid;

			if (n % sqr <= 1)
				return mid;
			else if (sqr > n)
				high = mid;
			else // sqr < n
				low = mid;
		}

		return n;
	}

	public static void main(String[] args) {

		System.out.println(squareRoot(25)); // must print 5
		System.out.println(squareRoot(256)); // must print 16
		System.out.println(squareRoot(10)); // must print 3

	}
}
