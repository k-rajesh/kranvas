package com.kranvas.console;

import com.kranvas.console.execution.CommandRegistrryLoader;
import com.kranvas.console.execution.ExecutionContext;
import com.kranvas.console.execution.ExecutionResult;
import com.kranvas.console.execution.Executor;
import com.kranvas.console.rendering.CanvasConsoleRenderer;

import java.util.Scanner;

public class EntryPoint {
    public static void main(String[] args) {
        Executor executor = new Executor(CommandRegistrryLoader.load(), new ExecutionContext(), new CanvasConsoleRenderer());
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("enter command: ");
            String command = scanner.nextLine();
            ExecutionResult executionResult = executor.execute(command);
            System.out.println(executionResult.getOutput());
            if (executionResult.shouldQuit())
                return;
        }
    }
}
