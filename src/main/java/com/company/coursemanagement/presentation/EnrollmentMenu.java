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
    public void show() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== ENROLLMENT MENU ===");
            System.out.println("1. Enroll student in course");
            System.out.println("2. View enrollments by student");
            System.out.println("3. View enrollments by course");
            System.out.println("4. View all enrollments");
            System.out.println("5. Cancel enrollment");
            System.out.println("0. Back");
            int option = readInt("Select an option: ");
            switch (option) {
                case 1 -> enrollStudent();
                case 2 -> viewByStudent();
                case 3 -> viewByCourse();
                case 4 -> viewAll();
                case 5 -> cancelEnrollment();
                case 0 -> back = true;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void enrollStudent() {
        Long studentId = (long) readInt("Student ID: ");
        Long courseId = (long) readInt("Course ID: ");
        try {
            Enrollment enrollment = enrollmentService.enroll(studentId, courseId);
            System.out.println("Enrollment created with ID: " + enrollment.getId());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewByStudent() {
        Long studentId = (long) readInt("Student ID: ");
        List<Enrollment> enrollments = enrollmentService.findByStudentId(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("Student has no enrollments");
            return;
        }
        enrollments.forEach(e ->
                System.out.println("Enrollment ID: " + e.getId() + " | Course ID: " + e.getCourseId() + " | Date: " + e.getEnrollmentDate() + " | Status: " + e.getStatus())
        );
    }

    private void viewByCourse() {
        Long courseId = (long) readInt("Course ID: ");
        List<Enrollment> enrollments = enrollmentService.findByCourseId(courseId);
        if (enrollments.isEmpty()) {
            System.out.println("Course has no enrollments");
            return;
        }
        enrollments.forEach(e ->
                System.out.println("Enrollment ID: " + e.getId() + " | Student ID: " + e.getStudentId() + " | Date: " + e.getEnrollmentDate() + " | Status: " + e.getStatus())
        );
    }

    private void viewAll() {
        List<Enrollment> enrollments = enrollmentService.findAll();
        if (enrollments.isEmpty()) {
            System.out.println("No enrollments registered");
            return;
        }
        enrollments.forEach(e ->
                System.out.println("ID: " + e.getId() + " | Student: " + e.getStudentId() + " | Course: " + e.getCourseId() + " | Status: " + e.getStatus())
        );
    }

    private void cancelEnrollment() {
        Long id = (long) readInt("Enrollment ID to cancel: ");
        try {
            enrollmentService.cancel(id);
            System.out.println("Enrollment cancelled successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
