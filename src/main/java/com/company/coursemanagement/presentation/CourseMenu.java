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
    public void show() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== COURSE MENU ===");
            System.out.println("1. Create course");
            System.out.println("2. Find course by ID");
            System.out.println("3. Find course by code");
            System.out.println("4. List all courses");
            System.out.println("5. Delete course");
            System.out.println("0. Back");
            int option = readInt("Select an option: ");
            switch (option) {
                case 1 -> createCourse();
                case 2 -> findCourseById();
                case 3 -> findCourseByCode();
                case 4 -> listCourses();
                case 5 -> deleteCourse();
                case 0 -> back = true;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void createCourse() {
        String code = readText("Course code: ");
        String name = readText("Course name: ");
        String description = readText("Description: ");
        Integer capacity = readInt("Max capacity: ");
        Course course = new Course(null, code, name, description, capacity);
        Course saved = courseService.save(course);
        System.out.println("Course created with ID: " + saved.getId());
    }

    private void findCourseById() {
        Long id = (long) readInt("Course ID: ");
        courseService.findById(id).ifPresentOrElse(
                c -> System.out.println("Code: " + c.getCode() + " | Name: " + c.getName() + " | Capacity: " + c.getMaxCapacity()),
                () -> System.out.println("Course not found")
        );
    }

    private void findCourseByCode() {
        String code = readText("Course code: ");
        courseService.findByCode(code).ifPresentOrElse(
                c -> System.out.println("ID: " + c.getId() + " | Name: " + c.getName() + " | Capacity: " + c.getMaxCapacity()),
                () -> System.out.println("Course not found")
        );
    }

    private void listCourses() {
        List<Course> courses = courseService.findAll();
        if (courses.isEmpty()) {
            System.out.println("No courses registered");
            return;
        }
        courses.forEach(c ->
                System.out.println("ID: " + c.getId() + " | " + c.getCode() + " | " + c.getName() + " | Capacity: " + c.getMaxCapacity())
        );
    }

    private void deleteCourse() {
        Long id = (long) readInt("Course ID to delete: ");
        try {
            courseService.deleteById(id);
            System.out.println("Course deleted successfully");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
