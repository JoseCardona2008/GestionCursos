package com.company.coursemanagement.presentation;

import java.util.Scanner;

public abstract class Menu {
    protected final Scanner scanner;

    protected Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract void mostrar();

    protected int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (true) {
            String linea = scanner.nextLine().trim();
            if (linea.isEmpty()) continue;
            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido");
                System.out.print(mensaje);
            }
        }
    }

    protected String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    protected void pausa() {
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
    }
}
