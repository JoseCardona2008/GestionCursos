package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.dto.StudentDTO;
import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.shared.Console;

import java.util.List;

public class StudentMenu {
    private final StudentService studentService;

    public StudentMenu(StudentService studentService) {
        this.studentService = studentService;
    }

    public void show() {
        Menu menu = new Menu("Student Menu")
                .addItem("Create", this::create)
                .addItem("Find By Id", this::findById)
                .addItem("List All", this::listAll)
                .addItem("Update", this::update)
                .addItem("Delete", this::delete);

        boolean running = true;
        while (running) {
            menu.show();
            int option = menu.readOption();
            running = menu.execute(option);
        }
    }

    private void create() {
        Console.println();
        Console.println("--- Create Student ---");
        StudentDTO dto = readStudentData();
        Student student = studentService.create(dto);
        Console.println("Student created: " + student);
    }

    private void findById() {
        Console.println();
        Console.println("--- Find Student By Id ---");
        Long id = Console.readLong("Enter id: ");
        try {
            Student student = studentService.findById(id);
            Console.println(student);
        } catch (BusinessException e) {
            printError(e);
        }
    }

    private void listAll() {
        Console.println();
        Console.println("--- List All Students ---");
        List<Student> students = studentService.findAll();
        if (students.isEmpty()) {
            Console.println("No students found.");
        } else {
            students.forEach(Console::println);
        }
    }

    private void update() {
        Console.println();
        Console.println("--- Update Student ---");
        Long id = Console.readLong("Enter id: ");
        try {
            StudentDTO dto = readStudentData();
            Student student = studentService.update(id, dto);
            Console.println("Student updated: " + student);
        } catch (BusinessException e) {
            printError(e);
        }
    }

    private void delete() {
        Console.println();
        Console.println("--- Delete Student ---");
        Long id = Console.readLong("Enter id: ");
        try {
            studentService.delete(id);
            Console.println("Student deleted.");
        } catch (BusinessException e) {
            printError(e);
        }
    }

    private StudentDTO readStudentData() {
        String firstName = Console.readLine("First name: ").trim();
        String lastName = Console.readLine("Last name: ").trim();
        String email = Console.readLine("Email: ").trim();
        java.time.LocalDate birthDate = Console.readDate("Birth date (dd/MM/yyyy): ");
        return new StudentDTO(firstName, lastName, email, birthDate);
    }

    private void printError(BusinessException e) {
        Console.println("Error: " + e.getMessage());
    }
}
