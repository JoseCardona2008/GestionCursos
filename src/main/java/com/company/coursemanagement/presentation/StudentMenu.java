package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.domain.model.Student;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class StudentMenu extends Menu {

    private final StudentService studentService;

    public StudentMenu(StudentService studentService, Scanner scanner) {
        super(scanner);
        this.studentService = studentService;
    }

    @Override
    public void mostrar() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n=== MENU ESTUDIANTES ===");
            System.out.println("1. Registrar estudiante");
            System.out.println("2. Buscar estudiante por ID");
            System.out.println("3. Listar todos los estudiantes");
            System.out.println("4. Eliminar estudiante");
            System.out.println("0. Volver");
            int opcion = leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> registrarEstudiante();
                case 2 -> buscarEstudiante();
                case 3 -> listarEstudiantes();
                case 4 -> eliminarEstudiante();
                case 0 -> volver = true;
                default -> System.out.println("Opcion no valida");
            }
        }
    }

    private void registrarEstudiante() {
        String nombre = leerTexto("Nombre: ");
        String apellido = leerTexto("Apellido: ");
        String correo = leerTexto("Correo: ");
        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        String fechaStr = scanner.nextLine().trim();
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(fechaStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha invalido");
            return;
        }
        Student student = new Student(null, nombre, apellido, correo, fecha);
        Student guardado = studentService.guardar(student);
        System.out.println("Estudiante registrado con ID: " + guardado.getId());
    }

    private void buscarEstudiante() {
        Long id = (long) leerEntero("ID del estudiante: ");
        studentService.buscarPorId(id).ifPresentOrElse(
                s -> System.out.println("Nombre: " + s.getFullName() + " | Correo: " + s.getEmail()),
                () -> System.out.println("Estudiante no encontrado")
        );
    }

    private void listarEstudiantes() {
        List<Student> estudiantes = studentService.listarTodos();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados");
            return;
        }
        estudiantes.forEach(s ->
                System.out.println("ID: " + s.getId() + " | " + s.getFullName() + " | " + s.getEmail())
        );
    }

    private void eliminarEstudiante() {
        Long id = (long) leerEntero("ID del estudiante a eliminar: ");
        try {
            studentService.eliminarPorId(id);
            System.out.println("Estudiante eliminado correctamente");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
