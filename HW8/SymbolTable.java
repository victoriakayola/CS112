/* 	
 * Purpose: Symbol table of variables (Strings) and associated values (of generic type), stored in a linked list (driver not included)
 */

package hw8;

import java.util.Iterator;

public class SymbolTable<Value> implements Dictionary<Value>, Iterable<String> {

	private class Node {
		String key;
		Value value;
		Node next;

		public Node(String k, Value v, Node p) {
			key = k;
			value = v;
			next = p;
		}
	}

	private Node head = null;

	private int size = 0;

	public void put(String k, Value value) {
		if (head == null) {
			head = new Node(k, value, null);
			size++;
		}
		if (contains(k))
			find(k).value = value;
		else
			putHelper(k, value, head);

	}

	public Node putHelper(String k, Value v, Node p) {
		if (p.next == null) {
			Node newNode = new Node(k, v, null);
			p.next = newNode;
			size++;
			return newNode;
		}
		if (k.compareTo(p.key) > 0 && k.compareTo(p.next.key) < 0) {
			
			Node newNode = new Node(k, v, null);
			newNode.next = p.next;
			p.next = newNode;
			size++;
			return newNode;
		} else
			return putHelper(k, v, p.next);
	}

	public Value get(String k) throws KeyNotFoundException {

		if (find(k) == null)
			throw new KeyNotFoundException();
		else
			return find(k).value;
	}

	public boolean contains(String k) {

		return (find(k) != null);

	}

	private Node find(String s) {
		return find(s, head);
	}

	private Node find(String k, Node p) {
		if (p == null)
			return null;
		if (p.key.compareTo(k) == 0)
			return p;
		else
			return find(k, p.next);

	}

	public boolean isEmpty() {
		return head == null;
	}

	public void delete(String k) {

		if (contains(k)) {
			if (head.key.compareTo(k) == 0) {
				head = head.next;
				size--;
			}

			else
				deleteHelper(k, head);
		}
	}

	private Node deleteHelper(String k, Node p) {
		if (k.compareTo(p.next.key) == 0) {
			p.next = p.next.next;
			size--;
			return p;
		} else
			return deleteHelper(k, p.next);

	}

	public String min() throws KeyNotFoundException {
		if (head == null)
			throw new KeyNotFoundException();
		else
			return head.key;
	}

	public String max() throws KeyNotFoundException {
		if (head == null)
			throw new KeyNotFoundException();
		else
			return lastKey(head);
	}

	// Helper for max()
	private String lastKey(Node p) {
		if (p.next == null)
			return p.key;
		else
			return lastKey(p.next);
	}

	public String floor(String k) throws KeyNotFoundException {
		if (isEmpty() || k.compareTo(min()) < 0)
			throw new KeyNotFoundException();
		if (contains(k))
			return k;
		else
			return floorHelper(k, head);
	}

	private String floorHelper(String k, Node p) {

		if (p.next == null)
			return p.key;

		if (k.compareTo(p.key) > 0 && k.compareTo(p.next.key) < 0)
			return p.key;
		else
			return floorHelper(k, p.next);
	}

	public String ceiling(String k) throws KeyNotFoundException {
		if (isEmpty() || k.compareTo(max()) > 0)
			throw new KeyNotFoundException();
		if (contains(k))
			return k;
		else
			return ceilingHelper(k, head);
	}

	private String ceilingHelper(String k, Node p) throws KeyNotFoundException {
		if (p.next == null)
			return p.key;

		if (k.compareTo(head.key) < 0)
			return head.key;
		if (k.compareTo(p.key) > 0 && k.compareTo(p.next.key) < 0)
			return p.next.key;
		else
			return ceilingHelper(k, p.next);
	}

	public int rank(String k) {
		return rankHelper(k, head, 0);
	}

	private int rankHelper(String k, Node p, int count) {
		if (p == null)
			return count;
		if (k.compareTo(p.key) <= 0) {
			return count;
		}
		return rankHelper(k, p.next, count + 1);
	}

	public String select(int r) throws KeyNotFoundException {

		if (r < 0 || r >= size())
			throw new KeyNotFoundException();

		return selectHelper(r, head, 0);
	}

	private String selectHelper(int r, Node p, int count) throws KeyNotFoundException {
		if (p.next == null) {
			return p.key;
		}
		if (rank(p.key) == r)
			return p.key;

		return selectHelper(r, p.next, count + 1);
	}

	public void deleteMin() {

		if (!isEmpty())
			delete(head.key);
	}

	public void deleteMax() {

		if (!isEmpty())
			deleteLast(head);

	}

	private Node deleteLast(Node p) {

		if (p.next == null) {
			delete(p.key);
			return p;
		}
		return deleteLast(p.next);
	}

	public int size() {
		return size;
	}

	public int size(String lo, String hi) { // TODO

		try {
			return rank(floor(hi)) - rank(ceiling(lo)) + 1;
		} catch (KeyNotFoundException e) {
			return 0;
		}

	}

	public Iterator<String> iterator() {
		return new It();
	}

	private class It implements Iterator<String> {

		private Node cursor;

		public It() {
			cursor = head;
		}

		public boolean hasNext() {
			return cursor != null;
		}

		public String next() {
			if (hasNext()) {
				String temp = cursor.key;
				cursor = cursor.next;
				return temp;
			} else {
				return null;
			}
		}

		public void remove() {
		}
	}

	public Iterator<String> iterator(String lo, String hi) {
		return new ItRange(lo, hi);
	}

	private class ItRange implements Iterator<String> {

		private Node cursor = null;
		private String hi;

		public ItRange(String lo, String hi) {
			this.hi = hi;
			try {
				cursor = find(ceiling(lo), head);
			} catch (KeyNotFoundException e) {
				cursor = null;
			}
		}

		public boolean hasNext() {
			if (cursor.key.compareTo(hi) < 0)
				return cursor.next != null;
			return false;
		}

		public String next() {

			if (hasNext()) {
				String ret = cursor.key;
				cursor = cursor.next;
				return ret;
			} else
				return null;
		}

		public void remove() {
		}
	}

	public String toString() {
		return toStringAux(head);
	}

	private String toStringAux(Node p) {
		if (p == null)
			return "";
		else if (p.next == null)
			return ("(" + p.key + "," + p.value + ")");
		else
			return "(" + p.key + "," + p.value + ") : " + toStringAux(p.next);
	}

	
}

class KeyNotFoundException extends Exception {
}
