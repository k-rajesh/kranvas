package com.kranvas.console.commands;

import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.console.execution.ExecutionResult;

/**
 * A command line command
 */
public interface Command {
    /**
     * The short name that the user is expected to type to invoke this command
     * @return a string representing the short name
     */
    String getShortName();

    /**
     * The description of the command to display in the usage page
     * @return a string describing the command
     */
    String getDescription();

    /**
     * Executes the given command using the parameters provided
     * @param executionContext the environment in which the commands are executing
     * @param params the string representing the arguments entered by the user excluding command name
     */
    void execute(ExecutionContext executionContext, String params);
}
