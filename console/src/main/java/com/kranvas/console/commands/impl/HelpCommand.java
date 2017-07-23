package com.kranvas.console.commands.impl;

import com.kranvas.console.commands.Command;
import com.kranvas.console.execution.ExecutionContext;

/**
 * Displays the usage summary
 */
public class HelpCommand implements Command {
    private static final String SHORT_NAME = "H";
    private static final String DESCRIPTION = "Help";

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
        executionContext.setPrintUsageRequested(true);
    }
}
