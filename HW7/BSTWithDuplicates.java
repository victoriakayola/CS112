/* 
 * Purpose: With a text file, creates an integer tree of the frequencies of words (driver not included)
 */
package hw7;

import java.util.LinkedList;
import java.util.Random;

public class BSTWithDuplicates {
	private class TreeNode {
		int key;
		String data;
		TreeNode left, right;

		TreeNode(int k, String d) {
			key = k;
			data = d;
			left = right = null;
		}
	}

	private TreeNode root = null;
	Random random = new Random();

	public void insert(int key, String data) {
		root = insert(key, data, root);
	}

	private TreeNode insert(int key, String data, TreeNode subtree) {

		if (subtree == null)
			return new TreeNode(key, data);

		if (key < subtree.key)
			subtree.left = insert(key, data, subtree.left);
		else if (key > subtree.key)
			subtree.right = insert(key, data, subtree.right);
		else {
			if (random.nextBoolean())
				subtree.right = insert(key, data, subtree.right);
			else
				subtree.left = insert(key, data, subtree.left);
		}

		return subtree;
	}

	public LinkedList<String> rangeSearch(int low, int high) {
		LinkedList<String> ret = new LinkedList<String>();
		rangeSearch(root, low, high, ret);
		return ret;
	}

	private void rangeSearch(TreeNode subtree, int low, int high, LinkedList<String> ret) {

		if (subtree == null)
			return;

		if (low < subtree.key)
			rangeSearch(subtree.left, low, high, ret);
		if (low <= subtree.key && high >= subtree.key)
			ret.add(subtree.data);
		if (high > subtree.key)
			rangeSearch(subtree.right, low, high, ret);
	}

	public int maxKey() {

		TreeNode current = root;

		if (current == null)
			return 0;

		while (current.right != null)
			current = current.right;

		return current.key;
	}

}