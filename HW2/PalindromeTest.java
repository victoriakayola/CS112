/* 
 * Purpose: This program tests if user input is a palindrome.
 */
package hw2;

import java.util.Scanner;

public class PalindromeTest {
	public static void main(String[] args) {

		// Print out welcome message

		System.out.println("\nWelcome to the Palindrome Test Program!");

		// Define a scanner for user input

		Scanner userInput = new Scanner(System.in);

		boolean keepGoing = true;

		while (keepGoing) {

			System.out.println("\nType in a sentence to be tested or \"quit\" to end: ");
			String input = userInput.nextLine();

			input = input.toLowerCase();

			if (input.equals("quit"))
				keepGoing = false;
			else {
				int length = input.length();
				for (int i = 0; i < length; i++) {
					char currentChar = input.charAt(i);
					if (!Character.isLetter(currentChar) && !Character.isDigit(currentChar)) {
						input = input.replace(Character.toString(currentChar), "");
						length = input.length();
					}
				}
				boolean palindrome = false;
				for (int i = 0; i < input.length() / 2; i++) {
					if (input.charAt(i) != input.charAt(input.length() - i - 1)) {
						palindrome = false;
					} else
						palindrome = true;

				}
				if (palindrome) {
					System.out.println("Palindrome!");
				} else
					System.out.println("Not a palindrome!");

			}

		}

		System.out.println("bye!");
	}
}
