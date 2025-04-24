package org.howard.edu.lspfinal.question2;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of tasks with operations to add, retrieve, and update tasks.
 */
public class TaskManager {
    private final Map<String, Task> tasks;

    /**
     * Constructs a new TaskManager with an empty task collection.
     */
    public TaskManager() {
        this.tasks = new HashMap<>();
    }

    /**
     * Adds a new task to the manager.
     *
     * @param name     the name of the task
     * @param priority the priority of the task
     * @param status   the status of the task
     * @throws DuplicateTaskException if a task with the same name already exists
     */
    public void addTask(String name, int priority, String status) throws DuplicateTaskException {
        if (tasks.containsKey(name)) {
            throw new DuplicateTaskException("Task '" + name + "' already exists.");
        }
        tasks.put(name, new Task(name, priority, status));
    }

    /**
     * Retrieves a task by its name.
     *
     * @param name the name of the task to retrieve
     * @return the task with the specified name
     * @throws TaskNotFoundException if no task with the specified name exists
     */
    public Task getTaskByName(String name) throws TaskNotFoundException {
        Task task = tasks.get(name);
        if (task == null) {
            throw new TaskNotFoundException("Task '" + name + "' not found.");
        }
        return task;
    }

    /**
     * Updates the status of an existing task.
     *
     * @param name   the name of the task to update
     * @param status the new status
     * @throws TaskNotFoundException if no task with the specified name exists
     */
    public void updateStatus(String name, String status) throws TaskNotFoundException {
        Task task = getTaskByName(name);
        task.setStatus(status);
    }

    /**
     * Prints all tasks grouped by their status.
     */
    public void printTasksGroupedByStatus() {
        Map<String, List<Task>> tasksByStatus = new HashMap<>();
        tasksByStatus.put("TODO", new ArrayList<>());
        tasksByStatus.put("IN_PROGRESS", new ArrayList<>());
        tasksByStatus.put("DONE", new ArrayList<>());

        for (Task task : tasks.values()) {
            tasksByStatus.get(task.getStatus()).add(task);
        }

        System.out.println("Tasks grouped by status:");
        for (Map.Entry<String, List<Task>> entry : tasksByStatus.entrySet()) {
            System.out.println(entry.getKey() + ":");
            for (Task task : entry.getValue()) {
                System.out.println("  " + task);
            }
        }
    }
} 