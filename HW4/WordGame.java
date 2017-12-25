/*
 * Purpose: Given a rectangle of letters, this game finds all words that consist of consecutive letters on the board (up-down, down-up, left-right, or right left) (driver not included)
 */

package hw4;

import java.util.ArrayList;

public class WordGame {
	
	public static String[] search(String[] dict, char[][] board) {

		ArrayList<String> ret = new ArrayList<String>();
		int height = board.length;

		int width = board.length == 0 ? 0 : board[0].length;

		for (int r = 0; r < height; r++) { // row

			for (int c = 0; c < width; c++) { // column
				
				String s = Character.toString(board[r][c]);
				if (HW4IO.inDictionary(dict, s))   //checks for one-letter words
					ret.add(s);

				for (int i = c + 1; i < width; i++) { // checking to the right--row doesn't change
					s += board[r][i];
					if (HW4IO.inDictionary(dict, s)) // check if s is in the dictionary
						ret.add(s);

				}
				s = Character.toString(board[r][c]);

				for (int i = c - 1; i >= 0; i--) { // check to the left
					s += board[r][i];
					if (HW4IO.inDictionary(dict, s))
						ret.add(s);

				}
				s = Character.toString(board[r][c]);
				for (int i = r + 1; i < height; i++) { // checking down
					s += board[i][c];
					if (HW4IO.inDictionary(dict, s))
						ret.add(s);

				}

				// r does change when you check up and down
				s = Character.toString(board[r][c]);
				for (int i = r - 1; i >= 0; i--) { // checking up
					s += board[i][c];
					if (HW4IO.inDictionary(dict, s))
						ret.add(s);

				}

			}
		}

		// This line converts ArrayList<String> to String []
		return ret.toArray(new String[0]);
	}
}