package com.kranvas.console;

import java.util.Scanner;

public class EntryPoint {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("enter command: ");
            String command = scanner.next();
            String params = scanner.nextLine();
            System.out.println("You asked me to do [" + command + "] with [" + params + "]");
            if ("Q".equals(command))
                return;
        }
    }
}
