package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;

/**
 * Requests the system to quit the program
 */
public class QuitCommand extends BaseCommand {
    private static final String SHORT_NAME = "Q";
    private static final String DESCRIPTION = "Exit the application";

    public QuitCommand() {
        super(SHORT_NAME, DESCRIPTION);
    }

    @Override
    public void execute(ExecutionContext executionContext, String params) {
        executionContext.setExitRequested(true);
    }
}
