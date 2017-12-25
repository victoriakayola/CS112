/* 
 * Purpose: With a text file, this creates a String tree of all the words with their frequencies (driver not included)
 */

package hw7;

public class CountingTree {

	private class TreeNode {
		String key;
		int count;
		TreeNode left, right;

		TreeNode(String key) {
			this.key = key;
			left = right = null;
			count = 1;
		}
	}

	private TreeNode root = null;
	private int totalEntries = 0;
	private int distinctEntries = 0;

	public int getTotalEntries() {
		return totalEntries;
	}

	public int getDistinctEntries() {
		return distinctEntries;
	}

	public void insert(String key) {

		totalEntries++;
		if (search(key) == 0) {
			distinctEntries++;
			root = insertRec(root, key);
		} else {
			TreeNode current = root;
			while (current.key.equals(key) == false) {

				if (current.key.compareTo(key) > 0)
					current = current.left;
				else
					current = current.right;

			}

			current.count++;

		}

	}

	private TreeNode insertRec(TreeNode root, String key) {

		if (root == null)
			return new TreeNode(key);

		if (key.compareTo(root.key) < 0)
			root.left = insertRec(root.left, key);
		else
			root.right = insertRec(root.right, key);

		return root;
	}

	public int search(String key) {

		if (root == null)
			return 0;

		TreeNode current = root;

		while (current != null) {
			if (current.key.compareTo(key) == 0) {
				return current.count;
			} else if (current.key.compareTo(key) > 0) {
				current = current.left;
			} else
				current = current.right;
		}

		return 0;
	}

	public BSTWithDuplicates frequencyTree() {
		BSTWithDuplicates t = new BSTWithDuplicates();
		frequencyTree(root, t);
		return t;
	}

	private void frequencyTree(TreeNode root, BSTWithDuplicates t) {
		if (root != null) {
			t.insert(root.count, root.key);
			frequencyTree(root.left, t);
			frequencyTree(root.right, t);
		}
	}

}