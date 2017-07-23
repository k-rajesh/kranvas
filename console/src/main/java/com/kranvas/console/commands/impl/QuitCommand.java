package com.kranvas.console.commands.impl;

import com.kranvas.console.commands.Command;
import com.kranvas.console.execution.ExecutionContext;

/**
 * Requests the system to quit the program
 */
public class QuitCommand implements Command {
    private static final String SHORT_NAME = "Q";
    private static final String DESCRIPTION = "Exit the application";

    @Override
    public String getShortName() {
        return SHORT_NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public void execute(ExecutionContext executionContext, String params) {
        executionContext.setExitRequested(true);
    }
}
