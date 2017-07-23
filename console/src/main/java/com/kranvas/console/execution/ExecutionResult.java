package com.kranvas.console.execution;

/**
 * Represents the result of a validation
 */
public class ExecutionResult {
    private boolean quit;
    private String output;

    static ExecutionResult output(String reason) {
        return new ExecutionResult(reason, false);
    }

    static ExecutionResult output(String reason, boolean quit) {
        return new ExecutionResult(reason, quit);
    }

    private ExecutionResult(String output, boolean quit) {
        this.output = output;
        this.quit = quit;
    }

    /**
     * The output for the command, if any
     * @return string containing the output
     */
    public String getOutput() {
        return output;
    }

    /**
     * Indicates whether the program should quit
     * @return boolean true when program should quit
     */
    public boolean shouldQuit() {
        return quit;
    }
}
