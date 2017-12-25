/* 
 * Purpose: This is a mock file system/directory tree with commands to manage it, similar to the Unix command line (driver not included)
 */
package hw7;

import java.util.LinkedList;

public class DirectoryTree {

	private class Node {
		String data;
		LinkedList<Node> children;
		Node parent;

		public Node(String d) {
			data = d;
			children = new LinkedList<>();
			parent = currentDir;
		}
	}

	private Node currentDir = new Node("");

	public boolean mkdir(String name) {
		if (currentDir.children.isEmpty()) {
			currentDir.children.add(new Node(name));
			return true;
		} else {

			for (Node c : currentDir.children) {
				if (c.data.equals(name))
					return false;
			}

			currentDir.children.add(new Node(name));
		}
		return true;
	}

	public boolean cd(String name) {

		for (Node c : currentDir.children) {
			if (c.data.equals(name)) {
				currentDir = c;
				return true;
			}
		}

		return false;
	}

	public boolean cdUp() {
		if (currentDir.data.equals(""))
			return false;

		currentDir = currentDir.parent;
		return true;
	}

	public boolean rmdir(String name) {

		for (Node c : currentDir.children) {
			if (c.data.equals(name)) {
				currentDir.children.remove(c);
				return true;
			}
		}

		return false;
	}

	public String ls() {
		String ret = "";

		for (Node c : currentDir.children) {
			ret += c.data + "\n";
		}

		return ret;
	}

	public String printSubTree() {
		return _printSubTree(currentDir, 0);
	}

	private String _printSubTree(Node n, int depth) {
		String ret = "";
		int i = 0;
		while (i < depth) {
			ret = ret + "  ";
			i++;
		}
		ret = ret + n.data.toString() + "\n";
		for (DirectoryTree.Node child : n.children) {
			ret = ret + _printSubTree(child, depth + 1);
		}
		return ret;
	}

	public String pwd() {

		String ret = "";
		if (currentDir.data.equals("")) {
			return "/";
		}

		Node cur = currentDir;
		while (!cur.data.equals("")) {
			ret = "/" + cur.data + ret;
			cur = cur.parent;
		}

		return ret;
	}

	public int numNodes() {
		return numNodes(currentDir);
	}

	public int numNodes(Node c) {
		int ret = 0;
		Node cur = c;

		for (Node a : cur.children) {
			ret += numNodes(a);
		}
		return 1 + ret;
	}
}