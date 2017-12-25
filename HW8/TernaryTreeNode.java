/* 	
 * Purpose: Node objects of a ternary search tree with recursive helper methods for add and contains 
 */

package hw8;

public class TernaryTreeNode {

	TernaryTreeNode left;
	TernaryTreeNode right;
	TernaryTreeNode center;
	char splitChar;

	public TernaryTreeNode(char c) {
		left = null;
		center = null;
		right = null;

		splitChar = c;
	}

	public static TernaryTreeNode add(TernaryTreeNode root, char[] input, int point) {
		if (point == input.length)
			return root;

		if (root == null) {

			root = new TernaryTreeNode(input[point]);

			root.center = add(root.center, input, point + 1);
			return root;
		} else if (input[point] == root.splitChar) {
			root.center = add(root.center, input, point + 1);
		} else if (input[point] < root.splitChar)
			root.left = add(root.left, input, point);
		else
			root.right = add(root.right, input, point);

		return root;
	}

	public static boolean contains(TernaryTreeNode n, char[] input, int point) {

		if (point == input.length)
			return false;

		if (n == null)
			return false;

		if (input[point] < n.splitChar)
			return contains(n.left, input, point);
		else if (input[point] > n.splitChar)
			return contains(n.right, input, point);
		else {

			if (n.center == null && point == input.length - 1)
				return true;
			else if (point == input.length - 1)
				return false;
			else
				return contains(n.center, input, point + 1);
		}
	}

}