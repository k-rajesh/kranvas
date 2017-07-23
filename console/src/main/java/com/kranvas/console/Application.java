package com.kranvas.console;

import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.console.execution.CommandRegistrryLoader;
import com.kranvas.console.execution.ExecutionResult;
import com.kranvas.console.execution.Executor;
import com.kranvas.console.rendering.CanvasConsoleRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Executor executor = new Executor(CommandRegistrryLoader.load(), new ExecutionContext(), new CanvasConsoleRenderer());
        Scanner scanner = new Scanner(System.in);
        logger.info("Kranvas started. Waiting for user input...");
        while(true) {
            System.out.print("enter command: ");
            String command = scanner.nextLine();

            logger.info("Executing command {}", command);
            ExecutionResult executionResult = executor.execute(command);

            logger.info("Command output is {}", executionResult.getOutput());
            System.out.println(executionResult.getOutput());

            if (executionResult.shouldQuit()) {
                logger.info("Existing the application as requested by the user");
                break;
            }
        }
    }
}