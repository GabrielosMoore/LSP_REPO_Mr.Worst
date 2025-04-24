package org.howard.edu.lspfinal.question2;

/**
 * Represents a task in the task management system.
 */
public class Task {
    private final String name;
    private final int priority;
    private String status;

    /**
     * Constructs a new Task with the specified name, priority, and status.
     *
     * @param name     the name of the task
     * @param priority the priority of the task (lower number = higher priority)
     * @param status   the status of the task ("TODO", "IN_PROGRESS", or "DONE")
     */
    public Task(String name, int priority, String status) {
        this.name = name;
        this.priority = priority;
        this.status = status;
    }

    /**
     * Returns the name of the task.
     *
     * @return the task name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the priority of the task.
     *
     * @return the task priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns the status of the task.
     *
     * @return the task status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("Task{name='%s', priority=%d, status='%s'}", name, priority, status);
    }
} 