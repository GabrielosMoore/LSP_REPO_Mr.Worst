package org.howard.edu.lsp.assignment6;

/**
 * Custom exception class for IntegerSet operations.
 * Thrown primarily when attempting operations like finding largest/smallest on an empty set.
 */
public class IntegerSetException extends Exception {

    /**
     * Default serial version ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new IntegerSetException with the specified detail message.
     * @param message the detail message.
     */
    public IntegerSetException(String message) {
        super(message);
    }
} 