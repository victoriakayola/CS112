/* 	
 * Purpose: Methods for a Ternary Search Tree, including add, contains, and an iterator 
 */

package hw8;

import java.util.*;

public class TernarySearchTree implements Iterable<String> {

	private TernaryTreeNode root;

	public TernarySearchTree() {
		this.root = null;
	}

	public static TernarySearchTree GetTestTree() {
		TernarySearchTree t = new TernarySearchTree();
		t.root = new TernaryTreeNode('c');
		t.root.left = new TernaryTreeNode('a');
		t.root.center = new TernaryTreeNode('u');
		t.root.right = new TernaryTreeNode('h');
		t.root.left.center = new TernaryTreeNode('t');
		t.root.center.center = new TernaryTreeNode('t');
		t.root.right.center = new TernaryTreeNode('e');
		t.root.right.right = new TernaryTreeNode('u');
		t.root.left.center.left = new TernaryTreeNode('s');
		t.root.center.center.left = new TernaryTreeNode('p');
		t.root.center.center.center = new TernaryTreeNode('e');
		t.root.right.right.left = new TernaryTreeNode('i');
		t.root.right.right.center = new TernaryTreeNode('s');
		return t;
	}

	public boolean contains(String s) {

		return TernaryTreeNode.contains(root, s.toCharArray(), 0);
	}

	public void add(String s) {

		root = TernaryTreeNode.add(root, s.toCharArray(), 0);

	}

	class TSTiterator implements Iterator<String> {

		Stack<TernaryTreeNode> nodeStack;
		Stack<String> prefixStack;;

		TSTiterator() {
			nodeStack = new Stack<>();
			prefixStack = new Stack<>();

			nodeStack.push(root);
			prefixStack.push("");

		}

		public String next() {

			if (hasNext()) {
				String ret = "";
				while (!nodeStack.isEmpty()) {

					TernaryTreeNode cur = nodeStack.pop();
					String prefix = prefixStack.pop();

					if (cur == null) {
						ret += prefix + "\n";
					}

					else {
						if (cur.right != null) {
							nodeStack.push(cur.right);
							prefixStack.push(prefix);
						}

						nodeStack.push(cur.center);
						String push = prefix + cur.splitChar;
						prefixStack.push(push);

						if (cur.left != null) {
							nodeStack.push(cur.left);
							prefixStack.push(prefix);
						}
					}
				}
				return ret;

			}
			return null;
		}

		public boolean hasNext() {

			return !nodeStack.isEmpty();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	@Override
	public Iterator<String> iterator() {
		return new TSTiterator();
	}

	public String toString() {
		Stack<TernaryTreeNode> nodeStack = new Stack<>();
		Stack<String> prefixStack = new Stack<>();

		if (root == null)
			return "";
		else {
			nodeStack.push(root);
			prefixStack.push("");
		}

		String ret = "";
		while (!nodeStack.isEmpty()) {

			TernaryTreeNode cur = nodeStack.pop();
			String prefix = prefixStack.pop();

			if (cur == null) {
				ret += prefix + "\n";
			}

			else {
				if (cur.right != null) {
					nodeStack.push(cur.right);
					prefixStack.push(prefix);
				}

				nodeStack.push(cur.center);
				String push = prefix + cur.splitChar;
				prefixStack.push(push);

				if (cur.left != null) {
					nodeStack.push(cur.left);
					prefixStack.push(prefix);
				}
			}
		}
		return ret;
	}
}