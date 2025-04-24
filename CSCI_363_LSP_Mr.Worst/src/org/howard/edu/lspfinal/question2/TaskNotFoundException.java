package org.howard.edu.lspfinal.question2;

/**
 * Custom exception thrown when attempting to access a task that does not exist.
 */
public class TaskNotFoundException extends Exception {
    /**
     * Constructs a new TaskNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
} 