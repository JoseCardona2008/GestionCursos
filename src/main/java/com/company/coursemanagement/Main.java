package com.company.coursemanagement;

import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.infrastructure.repository.InMemoryCourseRepository;
import com.company.coursemanagement.infrastructure.repository.InMemoryEnrollmentRepository;
import com.company.coursemanagement.infrastructure.repository.InMemoryStudentRepository;
import com.company.coursemanagement.presentation.CourseMenu;
import com.company.coursemanagement.presentation.EnrollmentMenu;
import com.company.coursemanagement.presentation.Menu;
import com.company.coursemanagement.presentation.StudentMenu;
import com.company.coursemanagement.shared.Console;

public class Main {
    public static void main(String[] args) {
        InMemoryStudentRepository studentRepository = new InMemoryStudentRepository();
        InMemoryCourseRepository courseRepository = new InMemoryCourseRepository();
        InMemoryEnrollmentRepository enrollmentRepository = new InMemoryEnrollmentRepository();

        StudentService studentService = new StudentService(studentRepository);
        CourseService courseService = new CourseService(courseRepository);
        EnrollmentService enrollmentService = new EnrollmentService(enrollmentRepository,
                studentRepository, courseRepository);

        StudentMenu studentMenu = new StudentMenu(studentService);
        CourseMenu courseMenu = new CourseMenu(courseService);
        EnrollmentMenu enrollmentMenu = new EnrollmentMenu(enrollmentService);

        Menu mainMenu = new Menu("Course Management System", "Exit")
                .addItem("Students", studentMenu::show)
                .addItem("Courses", courseMenu::show)
                .addItem("Enrollments", enrollmentMenu::show);

        Console.println("Welcome to the Course Management System!");

        boolean running = true;
        while (running) {
            mainMenu.show();
            int option = mainMenu.readOption();
            running = mainMenu.execute(option);
        }
        Console.println("Goodbye!");
    }
}
