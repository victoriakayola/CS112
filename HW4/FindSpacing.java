/*
 * Purpose: takes a string of letters and breaks it up into dictionary words in all possible ways (driver not included)
 */

package hw4;

import java.util.ArrayList;

public class FindSpacing {

	public static String[] space(String[] dict, String s) {
		ArrayList<String> ret = new ArrayList<String>();

		// base case
		if (s.length() == 0) {
			String[] r = { "" };
			return r;
		}


		int i = 2;
		while (i <= s.length()) { // i must be <= s.length() because the upper bound of substring is exclusive

			String pre = s.substring(0, i); // make a prefix of the first two letters of the string

			if (HW4IO.inDictionary(dict, pre)) { // check if prefix is in the dictionary

				String[] temp = space(dict, s.substring(i)); // if prefix is in the dictionary, call the space method
				// again and add words into this temporary array

				for (String e : temp) { // go through the temp array and add the words to ret with pre in front
					ret.add(pre + " " + e);
				}
			}
			i++;
		}

		// This line converts LinkedList<String> to String []
		return ret.toArray(new String[0]);
	}
}