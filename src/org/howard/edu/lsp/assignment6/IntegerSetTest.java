package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*; // Import JUnit 5 assertions
import org.junit.jupiter.api.*; // Import JUnit 5 annotations

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections; // Needed for sorting in toString test

/**
 * JUnit test class for IntegerSet.
 */
public class IntegerSetTest {

    private IntegerSet setA;
    private IntegerSet setB;
    private IntegerSet emptySet;

    // Initialize sets before each test method
    @BeforeEach
    public void setUp() {
        setA = new IntegerSet();
        setB = new IntegerSet();
        emptySet = new IntegerSet();
    }

    @Test
    @DisplayName("Test case for clear()")
    public void testClear() {
        setA.add(1);
        setA.add(2);
        assertFalse(setA.isEmpty(), "Set should not be empty after adding elements");
        setA.clear();
        assertTrue(setA.isEmpty(), "Set should be empty after clear()");
        assertEquals(0, setA.length(), "Length should be 0 after clear()");
    }

    @Test
    @DisplayName("Test case for length()")
    public void testLength() {
        assertEquals(0, emptySet.length(), "Length of empty set should be 0");
        setA.add(10);
        assertEquals(1, setA.length(), "Length should be 1 after adding one element");
        setA.add(20);
        setA.add(30);
        assertEquals(3, setA.length(), "Length should be 3 after adding three distinct elements");
        setA.add(20); // Add duplicate
        assertEquals(3, setA.length(), "Length should remain 3 after adding a duplicate element");
    }

    @Test
    @DisplayName("Test case for equals()")
    public void testEquals() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(1);
        IntegerSet setC = new IntegerSet();
        setC.add(1);
        setC.add(3);
        IntegerSet setD = new IntegerSet();
        setD.add(1);
        setD.add(2);

        assertTrue(setA.equals(setB), "Sets with same elements in different order should be equal");
        assertTrue(setB.equals(setA), "Equality should be symmetric");
        assertTrue(setA.equals(setD), "Sets with same elements in same order should be equal");
        assertFalse(setA.equals(setC), "Sets with different elements should not be equal");
        assertFalse(setA.equals(emptySet), "Non-empty set should not be equal to empty set");
        assertFalse(emptySet.equals(setA), "Empty set should not be equal to non-empty set");
        assertTrue(emptySet.equals(new IntegerSet()), "Two empty sets should be equal");
        assertTrue(setA.equals(setA), "A set should be equal to itself");

        // Test equals(Object) override
        Object objB = setB;
        Object objC = setC;
        Object notASet = new ArrayList<Integer>();

        assertTrue(setA.equals(objB), "[Object] Sets with same elements should be equal");
        assertFalse(setA.equals(objC), "[Object] Sets with different elements should not be equal");
        assertFalse(setA.equals(null), "[Object] Set should not be equal to null");
        assertFalse(setA.equals(notASet), "[Object] Set should not be equal to object of different type");
    }

    @Test
    @DisplayName("Test case for contains()")
    public void testContains() {
        setA.add(5);
        setA.add(10);
        assertTrue(setA.contains(5), "Set should contain 5");
        assertTrue(setA.contains(10), "Set should contain 10");
        assertFalse(setA.contains(15), "Set should not contain 15");
        assertFalse(emptySet.contains(0), "Empty set should not contain any element");
    }

    @Test
    @DisplayName("Test case for largest() - normal operation")
    public void testLargest() throws IntegerSetException {
        setA.add(10);
        setA.add(50);
        setA.add(20);
        assertEquals(50, setA.largest(), "Largest element should be 50");
        setB.add(-5);
        setB.add(-1);
        setB.add(-10);
        assertEquals(-1, setB.largest(), "Largest element should be -1");
        IntegerSet singleElementSet = new IntegerSet();
        singleElementSet.add(100);
        assertEquals(100, singleElementSet.largest(), "Largest element in single-element set should be the element itself");

    }

     @Test
    @DisplayName("Test case for largest() - exception on empty set")
    public void testLargestException() {
        Exception exception = assertThrows(IntegerSetException.class, () -> {
            emptySet.largest();
        });
        assertEquals("Cannot find largest element in an empty set.", exception.getMessage());
    }


    @Test
    @DisplayName("Test case for smallest() - normal operation")
    public void testSmallest() throws IntegerSetException {
         setA.add(10);
         setA.add(50);
         setA.add(20);
         assertEquals(10, setA.smallest(), "Smallest element should be 10");
         setB.add(-5);
         setB.add(-1);
         setB.add(-10);
         assertEquals(-10, setB.smallest(), "Smallest element should be -10");
         IntegerSet singleElementSet = new IntegerSet();
         singleElementSet.add(100);
         assertEquals(100, singleElementSet.smallest(), "Smallest element in single-element set should be the element itself");
    }

    @Test
    @DisplayName("Test case for smallest() - exception on empty set")
    public void testSmallestException() {
        Exception exception = assertThrows(IntegerSetException.class, () -> {
            emptySet.smallest();
        });
        assertEquals("Cannot find smallest element in an empty set.", exception.getMessage());
    }


    @Test
    @DisplayName("Test case for add()")
    public void testAdd() {
        assertTrue(setA.isEmpty(), "Set should start empty");
        setA.add(10);
        assertTrue(setA.contains(10), "Set should contain 10 after adding");
        assertEquals(1, setA.length(), "Length should be 1 after adding first element");
        setA.add(20);
        assertTrue(setA.contains(20), "Set should contain 20 after adding");
        assertEquals(2, setA.length(), "Length should be 2 after adding second element");
        setA.add(10); // Add duplicate
        assertEquals(2, setA.length(), "Length should remain 2 after adding duplicate");
        assertTrue(setA.contains(10), "Set should still contain 10 after adding duplicate");
    }

    @Test
    @DisplayName("Test case for remove()")
    public void testRemove() {
        setA.add(10);
        setA.add(20);
        setA.add(30);
        assertEquals(3, setA.length());

        setA.remove(20); // Remove existing middle element
        assertFalse(setA.contains(20), "Set should not contain 20 after removal");
        assertEquals(2, setA.length(), "Length should be 2 after removing 20");
        assertTrue(setA.contains(10), "Set should still contain 10");
        assertTrue(setA.contains(30), "Set should still contain 30");

        setA.remove(50); // Remove non-existing element
        assertEquals(2, setA.length(), "Length should remain 2 after attempting to remove non-existing element");

        setA.remove(10); // Remove first element
        assertFalse(setA.contains(10), "Set should not contain 10 after removal");
        assertEquals(1, setA.length(), "Length should be 1 after removing 10");

        setA.remove(30); // Remove last element
        assertFalse(setA.contains(30), "Set should not contain 30 after removal");
        assertEquals(0, setA.length(), "Length should be 0 after removing last element");
        assertTrue(setA.isEmpty(), "Set should be empty after removing all elements");

        emptySet.remove(100); // Remove from empty set
        assertTrue(emptySet.isEmpty(), "Removing from empty set should leave it empty");
    }

    @Test
    @DisplayName("Test case for union()")
    public void testUnion() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(3);
        setB.add(4);

        setA.union(setB); // {1, 2} U {2, 3, 4} = {1, 2, 3, 4}
        IntegerSet expectedUnion = new IntegerSet();
        expectedUnion.add(1);
        expectedUnion.add(2);
        expectedUnion.add(3);
        expectedUnion.add(4);

        assertEquals(expectedUnion, setA, "Union of {1, 2} and {2, 3, 4} should be {1, 2, 3, 4}");
        assertEquals(4, setA.length(), "Length after union should be 4");

        // Test union with empty set
        setA.clear();
        setA.add(5);
        setA.union(emptySet); // {5} U {} = {5}
        expectedUnion.clear();
        expectedUnion.add(5);
        assertEquals(expectedUnion, setA, "Union with empty set should not change the set");

        // Test empty set union with non-empty
        emptySet.union(setA); // {} U {5} = {5}
        assertEquals(expectedUnion, emptySet, "Empty set union with non-empty should result in the non-empty set");

        // Test union with self
        setA.clear();
        setA.add(1);
        setA.add(2);
        setA.union(setA); // {1, 2} U {1, 2} = {1, 2}
        expectedUnion.clear();
        expectedUnion.add(1); expectedUnion.add(2);
        assertEquals(expectedUnion, setA, "Union with self should not change the set");
    }

    @Test
    @DisplayName("Test case for intersect()")
    public void testIntersect() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(2);
        setB.add(3);
        setB.add(4);

        setA.intersect(setB); // {1, 2, 3} intersect {2, 3, 4} = {2, 3}
        IntegerSet expectedIntersect = new IntegerSet();
        expectedIntersect.add(2);
        expectedIntersect.add(3);

        assertEquals(expectedIntersect, setA, "Intersection of {1, 2, 3} and {2, 3, 4} should be {2, 3}");
        assertEquals(2, setA.length(), "Length after intersection should be 2");

        // Test intersect with disjoint set
        setA.clear(); setA.add(1); setA.add(2);
        setB.clear(); setB.add(3); setB.add(4);
        setA.intersect(setB); // {1, 2} intersect {3, 4} = {}
         assertTrue(setA.isEmpty(), "Intersection with disjoint set should be empty");

        // Test intersect with empty set
        setA.clear(); setA.add(1);
        setA.intersect(emptySet); // {1} intersect {} = {}
        assertTrue(setA.isEmpty(), "Intersection with empty set should be empty");

         // Test empty set intersect with non-empty
        setB.clear(); setB.add(1);
        emptySet.intersect(setB); // {} intersect {1} = {}
        assertTrue(emptySet.isEmpty(), "Empty set intersection with non-empty should be empty");

        // Test intersect with self
        setA.clear(); setA.add(1); setA.add(2);
        setA.intersect(setA); // {1, 2} intersect {1, 2} = {1, 2}
        expectedIntersect.clear(); expectedIntersect.add(1); expectedIntersect.add(2);
        assertEquals(expectedIntersect, setA, "Intersection with self should not change the set");

    }

     @Test
    @DisplayName("Test case for diff()")
    public void testDiff() {
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setB.add(2);
        setB.add(3);
        setB.add(4);

        setA.diff(setB); // {1, 2, 3} - {2, 3, 4} = {1}
        IntegerSet expectedDiff = new IntegerSet();
        expectedDiff.add(1);
        assertEquals(expectedDiff, setA, "Difference of {1, 2, 3} and {2, 3, 4} should be {1}");
        assertEquals(1, setA.length(), "Length after difference should be 1");

        // Test diff with disjoint set
        setA.clear(); setA.add(1); setA.add(2);
        setB.clear(); setB.add(3); setB.add(4);
        setA.diff(setB); // {1, 2} - {3, 4} = {1, 2}
        expectedDiff.clear(); expectedDiff.add(1); expectedDiff.add(2);
        assertEquals(expectedDiff, setA, "Difference with disjoint set should not change the set");

        // Test diff with empty set
        setA.clear(); setA.add(1);
        setA.diff(emptySet); // {1} - {} = {1}
        expectedDiff.clear(); expectedDiff.add(1);
        assertEquals(expectedDiff, setA, "Difference with empty set should not change the set");

        // Test empty set diff non-empty
        setB.clear(); setB.add(1);
        emptySet.diff(setB); // {} - {1} = {}
        assertTrue(emptySet.isEmpty(), "Empty set difference with non-empty should be empty");

        // Test diff with self
        setA.clear(); setA.add(1); setA.add(2);
        setA.diff(setA); // {1, 2} - {1, 2} = {}
        assertTrue(setA.isEmpty(), "Difference with self should result in empty set");
    }

    @Test
    @DisplayName("Test case for complement() - Symmetric Difference interpretation")
    public void testComplement() {
        setA.add(1);
        setA.add(2);
        setB.add(2);
        setB.add(3);

        setA.complement(setB); // {1, 2} complement {2, 3} = {1, 3} (Symmetric Difference)
        IntegerSet expectedComplement = new IntegerSet();
        expectedComplement.add(1);
        expectedComplement.add(3);
        assertEquals(expectedComplement, setA, "Complement (symmetric difference) of {1, 2} and {2, 3} should be {1, 3}");

        // Test complement with disjoint sets
        setA.clear(); setA.add(1); setA.add(2);
        setB.clear(); setB.add(3); setB.add(4);
        setA.complement(setB); // {1, 2} complement {3, 4} = {1, 2, 3, 4}
        expectedComplement.clear(); expectedComplement.add(1); expectedComplement.add(2); expectedComplement.add(3); expectedComplement.add(4);
        assertEquals(expectedComplement, setA, "Complement (symmetric difference) of disjoint sets should be their union");

        // Test complement with empty set
        setA.clear(); setA.add(1);
        setA.complement(emptySet); // {1} complement {} = {1}
        expectedComplement.clear(); expectedComplement.add(1);
        assertEquals(expectedComplement, setA, "Complement (symmetric difference) with empty set should not change the set");

         // Test empty set complement non-empty
        setB.clear(); setB.add(1);
        emptySet.complement(setB); // {} complement {1} = {1}
        assertEquals(setB, emptySet, "Empty set complement (symmetric difference) with non-empty should result in the non-empty set");

        // Test complement with self
        setA.clear(); setA.add(1); setA.add(2);
        setA.complement(setA); // {1, 2} complement {1, 2} = {}
        assertTrue(setA.isEmpty(), "Complement (symmetric difference) with self should be empty");
    }

    @Test
    @DisplayName("Test case for isEmpty()")
    public void testIsEmpty() {
        assertTrue(emptySet.isEmpty(), "Newly created set should be empty");
        setA.add(1);
        assertFalse(setA.isEmpty(), "Set should not be empty after adding an element");
        setA.remove(1);
        assertTrue(setA.isEmpty(), "Set should be empty after removing the only element");
        setB.add(1);
        setB.add(2);
        setB.clear();
        assertTrue(setB.isEmpty(), "Set should be empty after clear()");
    }

    @Test
    @DisplayName("Test case for toString()")
    public void testToString() {
        assertEquals("[]", emptySet.toString(), "toString of empty set should be '[]'");
        setA.add(10);
        assertEquals("[10]", setA.toString(), "toString of single element set should be '[10]'");
        setA.add(5);
        setA.add(15);
        // ArrayList toString order is based on insertion order, but equals checks content
        // For a predictable toString test, we might need to sort or check elements
        String result = setA.toString(); // e.g., "[10, 5, 15]"
        assertTrue(result.startsWith("[") && result.endsWith("]"), "toString should start with [ and end with ]");
        assertTrue(result.contains("10"), "toString should contain 10");
        assertTrue(result.contains("5"), "toString should contain 5");
        assertTrue(result.contains("15"), "toString should contain 15");
        assertEquals(3, setA.length()); // Ensure length matches elements shown

        // Alternative: Sort the internal list before checking toString for predictable order
        IntegerSet setC = new IntegerSet();
        setC.add(3); setC.add(1); setC.add(2);
        Collections.sort(setC.getInternalList()); // Sort underlying list
        assertEquals("[1, 2, 3]", setC.toString(), "toString after sorting should be '[1, 2, 3]'");
    }
} 