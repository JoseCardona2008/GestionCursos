package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.domain.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentMenu extends Menu {

    private final StudentService studentService;

    public StudentMenu(StudentService studentService, Scanner scanner) {
        super(scanner);
        this.studentService = studentService;
    }

    @Override
    public void show() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== STUDENT MENU ===");
            System.out.println("1. Register student");
            System.out.println("2. Find student by ID");
            System.out.println("3. List all students");
            System.out.println("4. Delete student");
            System.out.println("0. Back");
            int option = readInt("Select an option: ");
            switch (option) {
                case 1 -> registerStudent();
                case 2 -> findStudent();
                case 3 -> listStudents();
                case 4 -> deleteStudent();
                case 0 -> back = true;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void registerStudent() {
        String firstName = readText("First name: ");
        String lastName = readText("Last name: ");
        String email = readText("Email: ");
        System.out.print("Date of birth (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine().trim();
        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Invalid date format");
            return;
        }
        Student student = new Student(null, firstName, lastName, email, date);
        Student saved = studentService.save(student);
        System.out.println("Student registered with ID: " + saved.getId());
    }

    private void findStudent() {
        Long id = (long) readInt("Student ID: ");
        try {
            Student s = studentService.findById(id);
            System.out.println("Name: " + s.getFullName() + " | Email: " + s.getEmail());
        } catch (Exception e) {
            System.out.println("Student not found");
        }
    }

    private void listStudents() {
        List<Student> students = studentService.findAll();
        if (students.isEmpty()) {
            System.out.println("No students registered");
            return;
        }
        students.forEach(s ->
                System.out.println("ID: " + s.getId() + " | " + s.getFullName() + " | " + s.getEmail())
        );
    }

    private void deleteStudent() {
        Long id = (long) readInt("Student ID to delete: ");
        try {
            studentService.deleteById(id);
            System.out.println("Student deleted successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
