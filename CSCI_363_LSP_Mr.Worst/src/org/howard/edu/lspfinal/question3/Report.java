package org.howard.edu.lspfinal.question3;

/**
 * Abstract base class for all reports, implementing the template method pattern.
 * Defines the skeleton of the report generation algorithm.
 */
public abstract class Report {
    
    /**
     * Template method that defines the algorithm for generating a report.
     * This method is final to prevent subclasses from changing the workflow.
     */
    public final void generateReport() {
        loadData();
        formatData();
        printReport();
    }
    
    /**
     * Hook method for loading data specific to each report type.
     * Must be implemented by concrete subclasses.
     */
    protected abstract void loadData();
    
    /**
     * Hook method for formatting data specific to each report type.
     * Must be implemented by concrete subclasses.
     */
    protected abstract void formatData();
    
    /**
     * Hook method for printing the report in a format specific to each report type.
     * Must be implemented by concrete subclasses.
     */
    protected abstract void printReport();
} 