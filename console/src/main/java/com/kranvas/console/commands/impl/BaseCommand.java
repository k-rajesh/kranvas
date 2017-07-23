package com.kranvas.console.commands.impl;

import com.kranvas.console.commands.Command;

/**
 * Base class for all commands that accept numerical arguments
 */
abstract class BaseCommand implements Command {
    private final String shortName;
    private final String description;

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
