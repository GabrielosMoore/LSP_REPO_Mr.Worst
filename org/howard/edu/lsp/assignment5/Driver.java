package org.howard.edu.lsp.assignment5;

/**
 * Driver class to test IntegerSet implementation
 * This class contains the main method and demonstrates the usage of IntegerSet class
 * through various test cases
 * 
 * @author Your Name
 * @version 1.0
 * @since 2024-03-01
 */
public class Driver {
    /**
     * Main method to test the IntegerSet implementation
     * Demonstrates various operations including:
     * - Basic set operations (add, remove)
     * - Finding smallest and largest elements
     * - Set operations (union, intersection)
     * - Empty set operations
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Test case 1: Basic operations
        IntegerSet set1 = new IntegerSet();
        System.out.println("Set1 initially: " + set1);
        
        set1.add(1);
        set1.add(2);
        set1.add(3);
        System.out.println("After adding 1, 2, 3 to Set1: " + set1);
        System.out.println("Smallest value in Set1: " + set1.smallest());
        System.out.println("Largest value in Set1: " + set1.largest());
        
        // Test case 2: Union operation
        IntegerSet set2 = new IntegerSet();
        set2.add(3);
        set2.add(4);
        set2.add(5);
        
        System.out.println("\nSet2: " + set2);
        System.out.println("Union of Set1 and Set2");
        System.out.println("Before union - Set1: " + set1);
        System.out.println("Before union - Set2: " + set2);
        set1.union(set2);
        System.out.println("After union - Set1: " + set1);
        
        // Test case 3: Intersection operation
        IntegerSet set3 = new IntegerSet();
        set3.add(3);
        set3.add(4);
        set3.add(6);
        
        System.out.println("\nIntersection Test");
        System.out.println("Set1: " + set1);
        System.out.println("Set3: " + set3);
        set1.intersect(set3);
        System.out.println("After intersection: " + set1);
        
        // Test case 4: Empty and clear operations
        System.out.println("\nTesting isEmpty and clear");
        System.out.println("Is Set1 empty? " + set1.isEmpty());
        set1.clear();
        System.out.println("After clearing Set1");
        System.out.println("Is Set1 empty? " + set1.isEmpty());
    }
} 