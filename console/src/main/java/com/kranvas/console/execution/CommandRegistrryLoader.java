package com.kranvas.console.execution;

import com.kranvas.console.commands.impl.*;

/**
 * Initializes the command registry with known commands
 */
public class CommandRegistrryLoader {
    public static CommandRegistry load() {
        CommandRegistry registry = new CommandRegistry();
        registry.register(new HelpCommand());
        registry.register(new CreateCanvasCommand());
        registry.register(new LineCommand());
        registry.register(new RectangleCommand());
        registry.register(new BucketFillCommand());
        registry.register(new QuitCommand());
        return registry;
    }
}
