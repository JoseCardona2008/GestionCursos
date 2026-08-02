package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.dto.EnrollmentDTO;
import com.company.coursemanagement.application.service.EnrollmentService;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.model.Enrollment;
import com.company.coursemanagement.shared.Console;

import java.util.List;

public class EnrollmentMenu {
    private final EnrollmentService enrollmentService;

    public EnrollmentMenu(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    public void show() {
        Menu menu = new Menu("Enrollment Menu")
                .addItem("Create Enrollment", this::create)
                .addItem("Find By Id", this::findById)
                .addItem("List All", this::listAll)
                .addItem("Cancel Enrollment", this::cancel)
                .addItem("Delete Enrollment", this::delete);

        boolean running = true;
        while (running) {
            menu.show();
            int option = menu.readOption();
            running = menu.execute(option);
        }
    }

    private void create() {
        Console.println();
        Console.println("--- Create Enrollment ---");
        EnrollmentDTO dto = readEnrollmentData();
        try {
            Enrollment enrollment = enrollmentService.create(dto);
            Console.println("Enrollment created: " + enrollment);
        } catch (BusinessException e) {
            printError(e);
        }
    }

    private void findById() {
        Console.println();
        Console.println("--- Find Enrollment By Id ---");
        Long id = Console.readLong("Enter id: ");
        try {
            Enrollment enrollment = enrollmentService.findById(id);
            Console.println(enrollment);
        } catch (BusinessException e) {
            printError(e);
        }
    }

    private void listAll() {
        Console.println();
        Console.println("--- List All Enrollments ---");
        List<Enrollment> enrollments = enrollmentService.findAll();
        if (enrollments.isEmpty()) {
            Console.println("No enrollments found.");
        } else {
            enrollments.forEach(Console::println);
        }
    }

    private void cancel() {
        Console.println();
        Console.println("--- Cancel Enrollment ---");
        Long id = Console.readLong("Enter id: ");
        try {
            Enrollment enrollment = enrollmentService.cancel(id);
            Console.println("Enrollment cancelled: " + enrollment);
        } catch (BusinessException e) {
            printError(e);
        } catch (IllegalStateException e) {
            Console.println("Error: " + e.getMessage());
        }
    }

    private void delete() {
        Console.println();
        Console.println("--- Delete Enrollment ---");
        Long id = Console.readLong("Enter id: ");
        try {
            enrollmentService.delete(id);
            Console.println("Enrollment deleted.");
        } catch (BusinessException e) {
            printError(e);
        }
    }

    private EnrollmentDTO readEnrollmentData() {
        Long studentId = Console.readLong("Student id: ");
        Long courseId = Console.readLong("Course id: ");
        java.time.LocalDate enrollmentDate = Console.readDate("Enrollment date (dd/MM/yyyy): ");
        return new EnrollmentDTO(studentId, courseId, enrollmentDate);
    }

    private void printError(BusinessException e) {
        Console.println("Error: " + e.getMessage());
    }
}
