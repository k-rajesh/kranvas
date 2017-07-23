package com.kranvas.console.execution;

import com.kranvas.console.commands.Command;
import com.kranvas.console.rendering.CanvasConsoleRenderer;

/**
 * Executes commands given as strings
 */
public class Executor {
    private final CommandRegistry commandRegistry;
    private final ExecutionContext executionContext;
    private final CanvasConsoleRenderer canvasConsoleRenderer;

    public Executor(CommandRegistry commandRegistry, ExecutionContext executionContext, CanvasConsoleRenderer canvasConsoleRenderer) {
        this.commandRegistry = commandRegistry;
        this.executionContext = executionContext;
        this.canvasConsoleRenderer = canvasConsoleRenderer;
    }

    public ExecutionResult execute(String commandWithArg) {
        CommandNameAndArgs commandNameAndArgs = new CommandNameAndArgs(commandWithArg);
        Command command = commandRegistry.findCommand(commandNameAndArgs.getName());
        if (command == null)
            return ExecutionResult.output("Unknown command: " + commandNameAndArgs.getName() + System.lineSeparator() + commandRegistry.getUsage());

        StringBuilder sb = new StringBuilder();
        executeCommand(commandNameAndArgs, command, sb);
        printUsageIfRequested(sb);
        printCanvasIfRequested(sb);

        return ExecutionResult.output(sb.toString(), executionContext.isExitRequested());
    }

    private void printCanvasIfRequested(StringBuilder sb) {
        if (executionContext.isPrintCanvasRequested() && executionContext.getCanvas() != null) {
            executionContext.setPrintCanvasRequested(false);
            if (sb.length() > 0)
                sb.append(System.lineSeparator());
            sb.append(canvasConsoleRenderer.render(executionContext.getCanvas()));
        }
    }

    private void printUsageIfRequested(StringBuilder sb) {
        if (executionContext.isPrintUsageRequested()) {
            executionContext.setPrintUsageRequested(false);
            if (sb.length() > 0)
                sb.append(System.lineSeparator());
            sb.append(commandRegistry.getUsage());
        }
    }

    private void executeCommand(CommandNameAndArgs commandNameAndArgs, Command command, StringBuilder sb) {
        try {
            command.execute(executionContext, commandNameAndArgs.getArgs());
        } catch (IllegalArgumentException | IllegalStateException e) {
            sb.append(e.getMessage());
        }
    }

    private static class CommandNameAndArgs {
        private String name;
        private String args;

        CommandNameAndArgs(String fullCommandLine) {
            if (fullCommandLine != null && fullCommandLine.trim().length() > 0) {
                fullCommandLine = fullCommandLine.trim();
                int firstSpace = fullCommandLine.indexOf(' ');
                this.name = firstSpace > 0 ? fullCommandLine.substring(0, firstSpace) : fullCommandLine;
                this.args = firstSpace > 0 ? fullCommandLine.substring(firstSpace).trim() : "";
            }
        }

        String getName() {
            return name;
        }

        String getArgs() {
            return args;
        }
    }
}
