package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * References:
 * - Java ArrayList documentation: https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
 * - Set Operations: https://www.mathsisfun.com/sets/sets-introduction.html
 * - Java Collections utility class: https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html
 * - JavaDoc guidelines: https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html
 */

/**
 * IntegerSet class implements a set of integers using an ArrayList internally.
 * Provides standard set operations like union, intersection, difference, etc.
 * Ensures uniqueness of elements.
 *
 * @author Gabriel Moore
 * @version 1.1 (Updated for Assignment 6 with IntegerSetException)
 * @since 2024-03-01
 */
public class IntegerSet {
    private List<Integer> set = new ArrayList<Integer>();

    // Default Constructor
    public IntegerSet() {
    }

    /**
     * Constructor with initial ArrayList. Note: This constructor does NOT enforce
     * uniqueness on the provided list. Use the add method for unique additions.
     * Consider making this private or adding uniqueness check if needed for general use.
     * @param set ArrayList of integers to initialize the set
     */
    public IntegerSet(ArrayList<Integer> set) {
        // To ensure uniqueness if using this constructor publicly, uncomment below:
        // this.set = new ArrayList<>(new HashSet<>(set));
        this.set = set; // Keeping original behavior based on assignment 5 snippet
    }

    /**
     * Clears the internal representation of the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     * @return the length (cardinality) of the set.
     */
    public int length() {
        return set.size();
    }

    /**
     * Determines if this set is equal to another IntegerSet.
     * Two sets are equal if they contain exactly the same elements, regardless of order.
     * Note: The original spec's method signature was `equals(Object o)`.
     * This version uses `equals(IntegerSet b)` as shown in the Assignment 6 prompt.
     * If overriding Object.equals is required, the signature and implementation
     * need adjustment (check instanceof, casting, etc.).
     *
     * @param b The IntegerSet to compare against.
     * @return true if the sets contain the same elements, false otherwise.
     */
    public boolean equals(IntegerSet b) {
        if (b == null) {
            return false;
        }
        if (this.length() != b.length()) {
            return false;
        }
        // Use HashSet for efficient comparison ignoring order
        Set<Integer> thisHashSet = new HashSet<>(this.set);
        Set<Integer> otherHashSet = new HashSet<>(b.set);
        return thisHashSet.equals(otherHashSet);
    }

    // Overriding Object.equals based on common practice and Assignment 5 snippet:
    @Override
    public boolean equals(Object o) {
       if (this == o) return true;
       if (o == null || getClass() != o.getClass()) return false;
       IntegerSet otherSet = (IntegerSet) o;
       // Use the HashSet comparison logic for efficiency and order independence
       if (this.length() != otherSet.length()) return false;
       return new HashSet<>(this.set).equals(new HashSet<>(otherSet.set));
    }


    /**
     * Checks if the set contains the specified value.
     * @param value the integer value to check for.
     * @return true if the value is present in the set, false otherwise.
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest item in the set.
     * @return the largest integer in the set.
     * @throws IntegerSetException if the set is empty.
     */
    public int largest() throws IntegerSetException {
        if (set.isEmpty()) {
            throw new IntegerSetException("Cannot find largest element in an empty set.");
        }
        // Collections.max requires a non-empty collection
        return Collections.max(set);
    }

    /**
     * Returns the smallest item in the set.
     * @return the smallest integer in the set.
     * @throws IntegerSetException if the set is empty.
     */
    public int smallest() throws IntegerSetException {
        if (set.isEmpty()) {
            throw new IntegerSetException("Cannot find smallest element in an empty set.");
        }
        // Collections.min requires a non-empty collection
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if it is not already present.
     * If the item is already in the set, the set remains unchanged.
     * @param item the integer item to add.
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if it is present.
     * If the item is not in the set, the set remains unchanged.
     * @param item the integer item to remove.
     */
    public void remove(int item) {
        // ArrayList.remove(Object) is needed to remove the integer value,
        // not the element at a specific index.
        set.remove(Integer.valueOf(item));
    }

    /**
     * Performs the union operation modifies the current set
     * to contain all elements that are in the current set OR in intSetb (or both).
     * @param intSetb the IntegerSet to union with this set.
     */
    public void union(IntegerSet intSetb) {
        if (intSetb != null) {
            for (int item : intSetb.set) {
                this.add(item); // Use add to maintain uniqueness
            }
        }
    }

    /**
     * Performs the intersection operation modifies the current set
     * to contain only the elements that are present in BOTH the current set AND intSetb.
     * @param intSetb the IntegerSet to intersect with this set.
     */
    public void intersect(IntegerSet intSetb) {
         if (intSetb != null) {
            // Efficient intersection using retainAll with a HashSet view of the other set
            this.set.retainAll(new HashSet<>(intSetb.set));
        } else {
             // Intersecting with null arguably should result in an empty set?
             // Or throw an exception? Let's clear for now.
             this.clear();
         }
    }

    /**
     * Performs the set difference operation modifies the current set (s1)
     * to contain elements that are in the current set (s1) BUT NOT in intSetb (s2).
     * (s1 = s1 - s2)
     * @param intSetb the IntegerSet (s2) whose elements are to be removed from this set (s1).
     */
    public void diff(IntegerSet intSetb) {
         if (intSetb != null) {
            // Efficient difference using removeAll with a HashSet view of the other set
            this.set.removeAll(new HashSet<>(intSetb.set));
        }
        // If intSetb is null, diff arguably does nothing. Current set remains unchanged.
    }

    /**
     * Performs the complement operation modifies the current set.
     * Based on the ambiguous prompt "all elements not in s1" and the method signature,
     * this is interpreted as Symmetric Difference: elements in either set, but not both.
     * Alternatively, it could mean `intSetb - this` or `UniversalSet - this`.
     * Let's implement Symmetric Difference for this example.
     * (this = (this U intSetb) - (this intersect intSetb))
     *
     * @param intSetb the other IntegerSet.
     */
    public void complement(IntegerSet intSetb) {
        if (intSetb == null) return; // Complement with null arguably does nothing

        Set<Integer> currentElements = new HashSet<>(this.set);
        Set<Integer> otherElements = new HashSet<>(intSetb.set);

        Set<Integer> unionSet = new HashSet<>(currentElements);
        unionSet.addAll(otherElements); // Elements in either

        Set<Integer> intersectionSet = new HashSet<>(currentElements);
        intersectionSet.retainAll(otherElements); // Elements in both

        unionSet.removeAll(intersectionSet); // Remove elements in both from the union

        this.set = new ArrayList<>(unionSet);
    }


    /**
     * Checks if the set is empty (contains no elements).
     * @return true if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set.
     * The format matches the default `ArrayList.toString()` format (e.g., "[1, 2, 3]").
     * @return String representation of the set.
     */
    @Override
    public String toString() {
        return set.toString();
    }

    // Helper method for testing maybe (not part of public API)
    List<Integer> getInternalList() {
        return this.set;
    }
} 