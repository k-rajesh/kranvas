package com.kranvas.console.commands.impl;

import com.kranvas.console.commands.utils.IntegerListParser;
import com.kranvas.console.execution.ExecutionContext;

import java.util.List;

/**
 * Base class for all commands that accept numerical arguments
 */
abstract class NumericalArgumentCommand extends BaseCommand {
    private int numberOfArguments;

    NumericalArgumentCommand(String shortName, String description, int numberOfArguments) {
        super(shortName, description);
        this.numberOfArguments = numberOfArguments;
    }

    @Override
    public void execute(ExecutionContext executionContext, String params) {
        List<Integer> intParams = IntegerListParser.parse(params);
        if (intParams == null || intParams.size() < numberOfArguments)
            throw new IllegalArgumentException("Too few parameters, expecting " + numberOfArguments);

        if (intParams.size() > numberOfArguments)
            throw new IllegalArgumentException("Too many parameters provided, expecting " + numberOfArguments);

        execute(executionContext, intParams);
    }

    /**
     * Executes the command with the given list of numerical arguments
     * @param executionContext the execution context
     * @param intParams the list of numbers provided by the user
     */
    abstract void execute(ExecutionContext executionContext, List<Integer> intParams);
}
