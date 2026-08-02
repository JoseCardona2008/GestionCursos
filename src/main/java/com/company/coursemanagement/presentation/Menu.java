package com.company.coursemanagement.presentation;

import com.company.coursemanagement.shared.Console;

import java.util.ArrayList;
import java.util.List;

public final class Menu {
    private final String title;
    private final String backLabel;
    private final List<MenuItem> items = new ArrayList<>();

    public Menu(String title) {
        this(title, "Back");
    }

    public Menu(String title, String backLabel) {
        this.title = title;
        this.backLabel = backLabel;
    }

    public Menu addItem(String label, Runnable action) {
        items.add(new MenuItem(label, action));
        return this;
    }

    public void show() {
        Console.println();
        Console.println("==== " + title + " ====");
        for (int i = 0; i < items.size(); i++) {
            Console.println((i + 1) + ". " + items.get(i).label());
        }
        Console.println("0. " + backLabel);
    }

    public int readOption() {
        return Console.readInt("Select an option: ");
    }

    public boolean execute(int option) {
        Console.println();
        if (option == 0) {
            return false;
        }
        if (option < 1 || option > items.size()) {
            Console.println("Invalid option, please try again.");
            return true;
        }
        items.get(option - 1).action().run();
        return true;
    }

    private record MenuItem(String label, Runnable action) {
    }
}
