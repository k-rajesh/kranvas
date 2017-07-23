package com.kranvas.console.execution;

import com.kranvas.console.commands.Command;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Keeps track of the available commands
 */
public class CommandRegistry {
    private Map<String, Command>  registry = new LinkedHashMap<>();
    private String usage;

    public void register(Command command) {
        validateCommand(command);
        registry.put(command.getShortName(), command);
    }

    public Command findCommand(String shortName) {
        return registry.get(shortName);
    }

    public String getUsage() {
        if (usage == null)
            usage = buildUsage();
        return usage;
    }

    private String buildUsage() {
        final StringBuilder sb = new StringBuilder("The following are the commands supported by Kranvas");
        sb.append(System.lineSeparator());
        registry.forEach((name, command) -> formatCommandUsage(command, sb));
        return sb.toString();
    }

    private static void formatCommandUsage(Command command, StringBuilder sb) {
        sb
        .append(command.getShortName())
        .append("  ")
        .append(command.getDescription())
        .append(System.lineSeparator());
    }

    private void validateCommand(Command command) {
        if (command == null)
            throw new IllegalArgumentException("Command is null");

        if (command.getShortName() == null || command.getShortName().trim().length() == 0)
            throw new IllegalArgumentException("Command short name is null or blank");

        if (registry.containsKey(command.getShortName()))
            throw new IllegalArgumentException("Command short name " + command.getShortName() + " is already taken!");
    }
}
