package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.domain.model.Enrollment;

import java.util.List;
import java.util.Scanner;

public class EnrollmentMenu extends Menu {

    private final EnrollmentService enrollmentService;

    public EnrollmentMenu(EnrollmentService enrollmentService, Scanner scanner) {
        super(scanner);
        this.enrollmentService = enrollmentService;
    }

    @Override
    public void mostrar() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n=== MENU INSCRIPCIONES ===");
            System.out.println("1. Inscribir estudiante en curso");
            System.out.println("2. Ver inscripciones por estudiante");
            System.out.println("3. Ver inscripciones por curso");
            System.out.println("4. Ver todas las inscripciones");
            System.out.println("5. Cancelar inscripcion");
            System.out.println("0. Volver");
            int opcion = leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> inscribirEstudiante();
                case 2 -> verPorEstudiante();
                case 3 -> verPorCurso();
                case 4 -> verTodas();
                case 5 -> cancelarInscripcion();
                case 0 -> volver = true;
                default -> System.out.println("Opcion no valida");
            }
        }
    }

    private void inscribirEstudiante() {
        Long studentId = (long) leerEntero("ID del estudiante: ");
        Long courseId = (long) leerEntero("ID del curso: ");
        try {
            Enrollment enrollment = enrollmentService.inscribir(studentId, courseId);
            System.out.println("Inscripcion realizada con ID: " + enrollment.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void verPorEstudiante() {
        Long studentId = (long) leerEntero("ID del estudiante: ");
        List<Enrollment> inscripciones = enrollmentService.listarPorEstudiante(studentId);
        if (inscripciones.isEmpty()) {
            System.out.println("El estudiante no tiene inscripciones");
            return;
        }
        inscripciones.forEach(i ->
                System.out.println("Inscripcion ID: " + i.getId() + " | Curso ID: " + i.getCourseId() + " | Fecha: " + i.getEnrollmentDate() + " | Estado: " + i.getStatus())
        );
    }

    private void verPorCurso() {
        Long courseId = (long) leerEntero("ID del curso: ");
        List<Enrollment> inscripciones = enrollmentService.listarPorCurso(courseId);
        if (inscripciones.isEmpty()) {
            System.out.println("El curso no tiene inscripciones");
            return;
        }
        inscripciones.forEach(i ->
                System.out.println("Inscripcion ID: " + i.getId() + " | Estudiante ID: " + i.getStudentId() + " | Fecha: " + i.getEnrollmentDate() + " | Estado: " + i.getStatus())
        );
    }

    private void verTodas() {
        List<Enrollment> inscripciones = enrollmentService.listarTodos();
        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones registradas");
            return;
        }
        inscripciones.forEach(i ->
                System.out.println("ID: " + i.getId() + " | Estudiante: " + i.getStudentId() + " | Curso: " + i.getCourseId() + " | Estado: " + i.getStatus())
        );
    }

    private void cancelarInscripcion() {
        Long id = (long) leerEntero("ID de la inscripcion a cancelar: ");
        try {
            enrollmentService.cancelarInscripcion(id);
            System.out.println("Inscripcion cancelada correctamente");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
