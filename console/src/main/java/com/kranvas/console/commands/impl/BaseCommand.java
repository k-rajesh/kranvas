package com.kranvas.console.commands.impl;

import com.kranvas.console.commands.Command;
import com.kranvas.console.commands.utils.IntegerListParser;
import com.kranvas.console.execution.ExecutionContext;

import java.util.List;

/**
 * Base class for all commands that accept numerical arguments
 */
abstract class BaseCommand implements Command {
    private String shortName;
    private String description;

    BaseCommand(String shortName, String description) {
        this.shortName = shortName;
        this.description = description;
    }

    @Override
    public String getShortName() {
        return shortName;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
