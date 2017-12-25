/*
 * Purpose: Methods that manipulate Set objects (driver not included)
 */

package hw3;

public class Set {

	private int SIZE = 20; // length of the array

	private int[] S; // array holding the set

	private int next; // pointer to next available slot in array

	// Default constructor
	public Set() {
		S = new int[SIZE];
		next = 0;
	}

	// Construct this set consisting of exactly the elements of A
	public Set(int[] A) {

		this(); // calls default constructor to initialize S and next
		for (int i = 0; i < A.length; i++) {
			if (A[i] != 0 && i <= next) { // makes sure it doesn't insert trailing zeros
				insert(A[i]);
			}
		}

	}

	// Return an exact copy of this set
	public Set clone() {

		Set cloneSet = new Set(S); // makes new Set with the same array S values as this Set
		return cloneSet;
	}

	// Return a string representation of this set, e.g., [2,3,7,-3] or []
	public String toString() {
		if (next == 0) { // if set is empty
			return "[]";
		}

		String stringRepresentation = "[";

		for (int i = 0; i < next - 1; i++) {
			stringRepresentation += "" + this.S[i] + ",";
		}

		stringRepresentation += "" + this.S[next - 1] + "]";
		return stringRepresentation;
	}

	// Return the number of elements in this set.
	public int size() {

		return next;
	}

	// Return true if this is the empty set (has no members) and false otherwise
	public boolean isEmpty() {

		return (size() == 0);

	}

	// Return true if n is in the set and false otherwise.
	public boolean member(int k) {

		for (int i = 0; i < next; i++) {
			if (S[i] == k) {
				return true;
			}
		}

		return false;
	}

	// Returns true if this set is a subset of T, that is, every member
	// of this set is a member of T, and false otherwise
	public boolean subset(Set T) {
		boolean isSubset = true;

		for (int i = 0; i < this.next; i++) {

			if (!T.member(this.S[i])) {
				isSubset = false;
			}

		}
		return isSubset;
	}

	// Returns true if this set and T have exactly the same members
	public boolean equal(Set T) {

		return ((this.next == T.next) && (T.subset(this)) && (this.subset(T)));
	}

	// If the integer k is a member of the set already, do nothing,
	// otherwise, add to the set
	public void insert(int k) {

		if (!(member(k))) { // if k is not a member of the set

			if (next == S.length) {
				this.resize();
			}
			S[next] = k;
			next++;
		}

	}

	// If the integer k is not a member, do nothing; else remove it from the set
	public void delete(int k) {
		int indexK = 0;
		if (member(k)) {

			for (int i = 0; i < S.length; i++) { // finding the index of what I want to delete
				if (S[i] == k) {
					indexK = i;
				}
			}

			for (int i = indexK; i < next; i++) {
				S[i] = S[i + 1];
			}

			next--;
		}

	}

	// Return a new set consisting of all of the elements of this set and
	// T combined (without duplicates)
	public Set union(Set T) {
		Set s = this.clone();

		for (int i = 0; i < T.next; i++) {
			s.insert(T.S[i]);
		}

		return s;
	}

	// Return the intersection of this set and T.
	public Set intersection(Set T) {
		Set s = new Set();

		for (int i = 0; i < next; i++) {

			if (T.member(S[i])) {
				s.insert(S[i]);
			}
		}

		return s;
	}

	// Return the set difference of this set and T.
	public Set setdifference(Set T) {

		Set s = new Set();

		for (int i = 0; i < next; i++) {

			if (!T.member(S[i])) {
				s.insert(S[i]);
			}
		}

		return s;
	}

}
