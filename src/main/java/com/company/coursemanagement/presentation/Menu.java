package com.company.coursemanagement.presentation;

import java.util.Scanner;

public abstract class Menu {
    protected final Scanner scanner;

    protected Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract void show();

    protected int readInt(String message) {
        System.out.print(message);
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
                System.out.print(message);
            }
        }
    }

    protected String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    protected void pause() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }
}
