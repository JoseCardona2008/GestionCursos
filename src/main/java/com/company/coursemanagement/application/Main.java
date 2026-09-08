package com.company.coursemanagement.application;

import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.application.service.impl.CourseServiceImpl;
import com.company.coursemanagement.application.service.impl.EnrollmentServiceImpl;
import com.company.coursemanagement.application.service.impl.StudentServiceImpl;
import com.company.coursemanagement.domain.repository.InMemoryCourseRepository;
import com.company.coursemanagement.domain.repository.InMemoryEnrollmentRepository;
import com.company.coursemanagement.domain.repository.InMemoryStudentRepository;
import com.company.coursemanagement.presentation.CourseMenu;
import com.company.coursemanagement.presentation.EnrollmentMenu;
import com.company.coursemanagement.presentation.StudentMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InMemoryStudentRepository studentRepository = new InMemoryStudentRepository();
        InMemoryCourseRepository courseRepository = new InMemoryCourseRepository();
        InMemoryEnrollmentRepository enrollmentRepository = new InMemoryEnrollmentRepository();

        StudentService studentService = new StudentServiceImpl(studentRepository);
        CourseService courseService = new CourseServiceImpl(courseRepository);
        EnrollmentService enrollmentService = new EnrollmentServiceImpl(enrollmentRepository, studentRepository, courseRepository);

        Scanner scanner = new Scanner(System.in);

        StudentMenu studentMenu = new StudentMenu(studentService, scanner);
        CourseMenu courseMenu = new CourseMenu(courseService, scanner);
        EnrollmentMenu enrollmentMenu = new EnrollmentMenu(enrollmentService, scanner);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n=== COURSE MANAGEMENT ===");
            System.out.println("1. Students");
            System.out.println("2. Courses");
            System.out.println("3. Enrollments");
            System.out.println("0. Exit");
            int option = readInt(scanner, "Select an option: ");
            switch (option) {
                case 1 -> studentMenu.show();
                case 2 -> courseMenu.show();
                case 3 -> enrollmentMenu.show();
                case 0 -> exit = true;
                default -> System.out.println("Invalid option");
            }
        }
        System.out.println("Goodbye!");
        scanner.close();
    }

    private static int readInt(Scanner scanner, String message) {
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
}