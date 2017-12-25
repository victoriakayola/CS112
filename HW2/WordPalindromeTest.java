/* 
 * Purpose: This program tests if user input is a palindrome based on individual words.
 */
package hw2;

import java.util.Scanner;

public class WordPalindromeTest {

	public static void main(String[] args) {

		// Print out welcome message

		System.out.println("\nWelcome to the Word Palindrome Test Program!");

		// Define a scanner for user input

		Scanner userInput = new Scanner(System.in);

		boolean keepGoing = true;

		while (keepGoing) {

			System.out.println("\nType in a sentence to be tested or \"quit\" to end: ");
			String input = userInput.nextLine();

			input = input.toLowerCase();

			// System.out.println("your input: " + input);
			if (input.equals("quit"))
				keepGoing = false;
			else {
				int length = input.length();
				for (int i = 0; i < length; i++) {
					char currentChar = input.charAt(i);
					if (!Character.isLetter(currentChar) && !Character.isDigit(currentChar)
							&& !Character.isWhitespace(currentChar)) {
						input = input.replace(Character.toString(currentChar), "");
						length = input.length();
					}
				}

				String[] words = input.split("\\s+");

				System.out.print("[ ");
				for (int i = 0; i < words.length; i++) {
					if (i == words.length - 1)
						System.out.print(words[i] + " ");
					else
						System.out.print(words[i] + ", ");
				}
				System.out.print("]\n");

				boolean palindrome = false;
				for (int i = 0; i < words.length / 2; i++) {
					if (!(words[i].equals(words[words.length - i - 1]))) {
						palindrome = false;
					} else
						palindrome = true;

				}
				if (words.length == 1)
					palindrome = true;

				if (palindrome) {
					System.out.println("\nWord palindrome!");
				} else
					System.out.println("\nNot a Word Palindrome!");

			}

		}

		System.out.println("bye!");
	}

}
