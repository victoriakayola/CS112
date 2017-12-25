package hw6;

/*
 * Purpose: Methods to use with linked lists (driver not included)
 */

public class LinkedList<T extends Data<T>> {

	// Node class for the linked list
	public static class Node<T> {

		T item;
		Node<T> next;

		public Node(T n) { 
			item = n;
			next = null;
		}

		public Node(T n, Node<T> p) { 
			item = n;
			next = p;
		}
	}

	public Node<T> head;

	//returns the length of the linked list
	public int length() {
		int length = 0;

		Node<T> current = head;

		while (current != null) {
			length++;
			current = current.next;
		}

		return length;
	}

	//returns the number of numbers in the list that are odd
	public int numOdd() {

		int oddCount = 0;

		Node<T> current = head;

		while (current != null) {
			if (current.item.isOdd()) {
				oddCount++;
			}
			current = current.next;
		}

		return oddCount;
	}

	//returns the average of all numbers in the linked list
	public double avg() {

		double sumOfValues = 0;

		Node<T> current = head;

		if (current == null)
			return -1;

		while (current != null) {
			sumOfValues += current.item.getDoubleValue();
			current = current.next;
		}

		return sumOfValues / length();
	}

	//returns the item at the nth position in the list
	public T nth(int n) throws NotPresentInLinkedList {

		if (n >= length()) {
			throw new NotPresentInLinkedList();
		}

		Node<T> current = head;
		int position = 0;

		while (position < n) {
			position++;
			current = current.next;
		}

		return current.item;

	}

	//returns if the list is in ascending order
	public boolean inAscendingOrder() { 

		if (length() <= 1)
			return true;

		Node<T> previous = head;
		Node<T> current = previous.next;

		while (current != null) {
			int compValue = previous.item.compareTo(current.item);

			if (compValue >= 0)
				return false;

			previous = current;
			current = current.next;
		}

		return true;
	}

	//returns if two lists are equal
	public boolean areEqual(LinkedList<T> h2) {
		if (this.length() != h2.length())
			return false;

		Node<T> thisPointer = this.head;
		Node<T> thatPointer = h2.head;

		while (thisPointer != null) {

			if (thisPointer.item.compareTo(thatPointer.item) != 0)
				return false;

			thisPointer = thisPointer.next;
			thatPointer = thatPointer.next;
		}

		return true;

	}

	//returns a copy of the list
	public LinkedList<T> copy() {

		LinkedList<T> listCopy = new LinkedList<T>();

		if (this.head == null)
			return listCopy;

		Node<T> current = this.head;

		listCopy.head = new Node<T>(current.item.makeCopy());
		
		if (length() == 1)
			return listCopy;
		else current = current.next;

		Node<T> newListCurrent = listCopy.head;

		while (current != null) {

			newListCurrent.next = new Node<T>(current.item.makeCopy());

			newListCurrent = newListCurrent.next;
			current = current.next;
		}

		return listCopy;

	}

	
}