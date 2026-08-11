package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.domain.model.Course;

import java.util.List;
import java.util.Scanner;

public class CourseMenu extends Menu {

    private final CourseService courseService;

    public CourseMenu(CourseService courseService, Scanner scanner) {
        super(scanner);
        this.courseService = courseService;
    }

    @Override
    public void mostrar() {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n=== MENU CURSOS ===");
            System.out.println("1. Crear curso");
            System.out.println("2. Buscar curso por ID");
            System.out.println("3. Buscar curso por codigo");
            System.out.println("4. Listar todos los cursos");
            System.out.println("5. Eliminar curso");
            System.out.println("0. Volver");
            int opcion = leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> crearCurso();
                case 2 -> buscarCursoPorId();
                case 3 -> buscarCursoPorCodigo();
                case 4 -> listarCursos();
                case 5 -> eliminarCurso();
                case 0 -> volver = true;
                default -> System.out.println("Opcion no valida");
            }
        }
    }

    private void crearCurso() {
        String codigo = leerTexto("Codigo del curso: ");
        String nombre = leerTexto("Nombre del curso: ");
        String descripcion = leerTexto("Descripcion: ");
        Integer capacidad = leerEntero("Capacidad maxima: ");
        Course course = new Course(null, codigo, nombre, descripcion, capacidad);
        Course guardado = courseService.guardar(course);
        System.out.println("Curso creado con ID: " + guardado.getId());
    }

    private void buscarCursoPorId() {
        Long id = (long) leerEntero("ID del curso: ");
        courseService.buscarPorId(id).ifPresentOrElse(
                c -> System.out.println("Codigo: " + c.getCode() + " | Nombre: " + c.getName() + " | Capacidad: " + c.getMaxCapacity()),
                () -> System.out.println("Curso no encontrado")
        );
    }

    private void buscarCursoPorCodigo() {
        String codigo = leerTexto("Codigo del curso: ");
        courseService.buscarPorCodigo(codigo).ifPresentOrElse(
                c -> System.out.println("ID: " + c.getId() + " | Nombre: " + c.getName() + " | Capacidad: " + c.getMaxCapacity()),
                () -> System.out.println("Curso no encontrado")
        );
    }

    private void listarCursos() {
        List<Course> cursos = courseService.listarTodos();
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados");
            return;
        }
        cursos.forEach(c ->
                System.out.println("ID: " + c.getId() + " | " + c.getCode() + " | " + c.getName() + " | Capacidad: " + c.getMaxCapacity())
        );
    }

    private void eliminarCurso() {
        Long id = (long) leerEntero("ID del curso a eliminar: ");
        try {
            courseService.eliminarPorId(id);
            System.out.println("Curso eliminado correctamente");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
