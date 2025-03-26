package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IntegerSet class implements a set of integers with various set operations.
 * This class provides functionality for managing a set of integers including
 * basic set operations like union, intersection, and complement.
 * 
 * @author Your Name
 * @version 1.0
 * @since 2024-03-01
 */
public class IntegerSet {
    /** The internal ArrayList to store the set elements */
    private List<Integer> set = new ArrayList<Integer>();

    /**
     * Default Constructor - Creates an empty set
     */
    public IntegerSet() {
    }

    /**
     * Constructor with initial set of integers
     * @param set ArrayList of integers to initialize the set
     */
    public IntegerSet(ArrayList<Integer> set) {
        this.set = set;
    }

    /**
     * Clears the internal representation of the set
     * Removes all elements from the set
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the length of the set
     * @return The number of elements in the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Determines if two sets are equal
     * Two sets are equal if they contain all of the same values in ANY order
     * 
     * @param o Object to compare with
     * @return true if sets are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof IntegerSet)) return false;
        
        IntegerSet other = (IntegerSet) o;
        if (this.length() != other.length()) return false;
        
        ArrayList<Integer> temp1 = new ArrayList<>(this.set);
        ArrayList<Integer> temp2 = new ArrayList<>(other.set);
        Collections.sort(temp1);
        Collections.sort(temp2);
        return temp1.equals(temp2);
    }

    /**
     * Checks if a value exists in the set
     * @param value The integer value to check for
     * @return true if value exists in the set, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Finds the largest item in the set
     * @return The largest integer in the set
     * @throws RuntimeException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Finds the smallest item in the set
     * @return The smallest integer in the set
     * @throws RuntimeException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if it's not already present
     * @param item The integer to add to the set
     */
    public void add(int item) {
        if (!contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if it exists
     * @param item The integer to remove from the set
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Performs union operation with another set
     * Adds all elements from intSetb to this set if they're not already present
     * 
     * @param intSetb The set to perform union with
     */
    public void union(IntegerSet intSetb) {
        for (Integer item : intSetb.set) {
            add(item);
        }
    }

    /**
     * Performs intersection operation with another set
     * Keeps only elements that exist in both sets
     * 
     * @param intSetb The set to perform intersection with
     */
    public void intersect(IntegerSet intSetb) {
        set.retainAll(intSetb.set);
    }

    /**
     * Performs difference operation (this set - intSetb)
     * Removes all elements that exist in intSetb from this set
     * 
     * @param intSetb The set to subtract from this set
     */
    public void diff(IntegerSet intSetb) {
        set.removeAll(intSetb.set);
    }

    /**
     * Performs complement operation with another set
     * Results in a set containing elements that are in intSetb but not in this set
     * 
     * @param intSetb The set to perform complement with
     */
    public void complement(IntegerSet intSetb) {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer item : intSetb.set) {
            if (!this.contains(item)) {
                result.add(item);
            }
        }
        this.set = result;
    }

    /**
     * Checks if the set is empty
     * @return true if the set has no elements, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns string representation of the set
     * @return A string representation of the set in the format [element1, element2, ...]
     */
    @Override
    public String toString() {
        return set.toString();
    }
} 