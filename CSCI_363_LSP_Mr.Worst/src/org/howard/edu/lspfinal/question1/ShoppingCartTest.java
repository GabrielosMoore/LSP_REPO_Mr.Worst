package org.howard.edu.lspfinal.question1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ShoppingCartTest {
    
    @Test
    @DisplayName("Test for adding valid item")
    public void testAddValidItem() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Laptop", 999.99);
        assertEquals(999.99, cart.getTotalCost());
    }
    
    @Test
    @DisplayName("Test for adding item with 0 price")
    public void testAddItemWithZeroPrice() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> {
            cart.addItem("Free Item", 0.0);
        });
    }
    
    @Test
    @DisplayName("Test for adding item with negative price")
    public void testAddItemWithNegativePrice() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> {
            cart.addItem("Invalid Item", -10.0);
        });
    }
    
    @Test
    @DisplayName("Test for applying SAVE10")
    public void testApplySave10() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Laptop", 1000.0);
        cart.applyDiscountCode("SAVE10");
        assertEquals(10.0, cart.getDiscountPercentage());
        assertEquals(900.0, cart.getTotalCost());
    }
    
    @Test
    @DisplayName("Test for applying SAVE20")
    public void testApplySave20() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Laptop", 1000.0);
        cart.applyDiscountCode("SAVE20");
        assertEquals(20.0, cart.getDiscountPercentage());
        assertEquals(800.0, cart.getTotalCost());
    }
    
    @Test
    @DisplayName("Test for applying invalid code")
    public void testApplyInvalidCode() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class, () -> {
            cart.applyDiscountCode("SAVE50");
        });
    }
    
    @Test
    @DisplayName("Test total cost without discount")
    public void testTotalCostWithoutDiscount() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Laptop", 1000.0);
        cart.addItem("Mouse", 50.0);
        assertEquals(1050.0, cart.getTotalCost());
    }
    
    @Test
    @DisplayName("Test total cost with discount")
    public void testTotalCostWithDiscount() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Laptop", 1000.0);
        cart.addItem("Mouse", 50.0);
        cart.applyDiscountCode("SAVE10");
        assertEquals(945.0, cart.getTotalCost());
    }
    
    @Test
    @DisplayName("Test total cost with empty cart")
    public void testTotalCostWithEmptyCart() {
        ShoppingCart cart = new ShoppingCart();
        assertEquals(0.0, cart.getTotalCost());
    }
} 