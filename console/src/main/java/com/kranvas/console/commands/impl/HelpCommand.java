package com.kranvas.console.commands.impl;

import com.kranvas.console.execution.ExecutionContext;

/**
 * Displays the usage summary
 */
public class HelpCommand extends BaseCommand {
    private static final String SHORT_NAME = "H";
    private static final String DESCRIPTION = "Help";

    public HelpCommand() {
        super(SHORT_NAME, DESCRIPTION);
    }

    @Override
    public void execute(ExecutionContext executionContext, String params) {
        executionContext.setPrintUsageRequested(true);
    }
}
