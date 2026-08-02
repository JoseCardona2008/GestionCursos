package com.company.coursemanagement.shared;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class Console {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Console() {
    }

    public static String readLine(String prompt) {
        print(prompt);
        try {
            return READER.readLine();
        } catch (Exception e) {
            throw new RuntimeException("Error reading input", e);
        }
    }

    public static String readLine() {
        try {
            return READER.readLine();
        } catch (Exception e) {
            throw new RuntimeException("Error reading input", e);
        }
    }

    public static Integer readInt(String prompt) {
        while (true) {
            try {
                String value = readLine(prompt).trim();
                return Integer.valueOf(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again.");
            }
        }
    }

    public static Long readLong(String prompt) {
        while (true) {
            try {
                String value = readLine(prompt).trim();
                return Long.valueOf(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, please try again.");
            }
        }
    }

    public static LocalDate readDate(String prompt) {
        while (true) {
            String value = readLine(prompt).trim();
            try {
                return LocalDate.parse(value, FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format (dd/MM/yyyy), please try again.");
            }
        }
    }

    public static void print(String message) {
        System.out.print(message);
    }

    public static void println(Object object) {
        System.out.println(String.valueOf(object));
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void println() {
        System.out.println();
    }
}
