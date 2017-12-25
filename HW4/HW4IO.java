/*
 * Purpose: Helper methods for FindSpacing and WordGame
 */

package hw4;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * This class is used by:
 * 1. FindSpacing.java
 * 2. FindSpacingDriver.java
 * 3. WordGame.java
 * 4. WordGameDriver.java
 */
public class HW4IO {

	/*
	 * Returns true if an only the string s is equal to one of the strings in dict.
	 * Assumes dict is in alphabetical order.
	 */
	public static boolean inDictionary(String[] dict, String s) { 

		int begin = 0;
		int end = dict.length - 1;

		while (begin <= end) {
			int mid = (begin + end) / 2;

			if (s.compareTo(dict[mid]) == 0) {
				return true;
			}

			else if (s.compareTo(dict[mid]) > 0) {
				begin = mid + 1; // change lower bound
			}

			else {
				end = mid - 1; // change upper bound
			}
		}
		return false;
	}

	/*
	 * Returns true if and only if dict is in alphabetical order
	 */
	public static boolean checkAlphaOrder(String[] dict) { 

		boolean inOrder = true;

		for (int i = 0; i < dict.length - 1; i++) {
			if (dict[i].compareTo(dict[i + 1]) > 0) {
				inOrder = false;
			}
		}

		return inOrder;

		
	}

	/*
	 * Reads in a dictionary file (one word per line) and checks if it's in
	 * alphabetical order. Returns null in case of failure.
	 */
	public static String[] readDictionary(String dictionaryFileName) {
		Scanner fileScanner;

		// Open the dictionary file
		try {
			fileScanner = new Scanner(new File(dictionaryFileName));
		} catch (IOException e) {
			System.err.println("Unable to open dictionary file. " + e.getMessage());
			System.err.println("Currently in directory " + System.getProperty("user.dir"));
			return null;
		}

		// read the dictionary file
		LinkedList<String> prelimDict = new LinkedList<String>();
		while (fileScanner.hasNext()) {
			prelimDict.add(fileScanner.next().toLowerCase());
		}
		String[] dict = prelimDict.toArray(new String[0]);
		fileScanner.close();
		if (checkAlphaOrder(dict)) {
			return dict;
		} else {
			System.err.println("Error: dictionary not in alphabetical order.");
			return null;
		}
	}

	/*
	 * Returns true if and only if board is a rectangular array: that is,
	 * board[i].length is the same for every i from 0 to board.length-1
	 */
	public static boolean checkIfRectangle(char[][] board) { 


		boolean isRectangle = true;

		if (board.length == 0 || board.length == 1) {
			return true;
		}

		for (int i = 0; i < board.length - 1; i++) {
			if (board[i].length != board[i + 1].length) {
				return false;
			}

		}

		return isRectangle;
	}

	/*
	 * Converts a text file into a two-dimensional array of characters, where
	 * a[i][j] is the character in row i from the top (starting at 0) and column j
	 * from the left (starting at 0). Newline characters are not included in the
	 * array. Checks that the array is rectangular. Returns null in case of failure.
	 */
	public static char[][] readBoard(String boardFileName) {
		Scanner fileScanner;
		// open the board file
		try {
			fileScanner = new Scanner(new File(boardFileName));
		} catch (IOException e) {
			System.out.println("Unable to open board file " + e.getMessage());
			System.out.println("Currently in directory " + System.getProperty("user.dir"));
			return null;
		}

		// read the board file
		LinkedList<char[]> prelimBoard = new LinkedList<char[]>();
		while (fileScanner.hasNext()) {
			prelimBoard.add(fileScanner.next().toLowerCase().toCharArray());
		}
		char[][] board = prelimBoard.toArray(new char[0][]);
		fileScanner.close();

		if (checkIfRectangle(board)) {
			return board;
		} else {
			System.err.println("Error: board is not rectangular.");
			return null;
		}
	}

}