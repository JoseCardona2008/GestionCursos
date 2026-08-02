package com.company.coursemanagement.presentation;

import com.company.coursemanagement.application.dto.CourseDTO;
import com.company.coursemanagement.application.service.CourseService;
import com.company.coursemanagement.domain.exception.BusinessException;
import com.company.coursemanagement.domain.model.Course;
import com.company.coursemanagement.shared.Console;

import java.util.List;

public class CourseMenu {
    private final CourseService courseService;

    public CourseMenu(CourseService courseService) {
        this.courseService = courseService;
    }

    public void show() {
        Menu menu = new Menu("Course Menu")
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
        Console.println("--- Create Course ---");
        CourseDTO dto = readCourseData();
        Course course = courseService.create(dto);
        Console.println("Course created: " + course);
    }

    private void findById() {
        Console.println();
        Console.println("--- Find Course By Id ---");
        Long id = Console.readLong("Enter id: ");
        try {
            Course course = courseService.findById(id);
            Console.println(course);
        } catch (BusinessException e) {
            printError(e);
        }
    }

    private void listAll() {
        Console.println();
        Console.println("--- List All Courses ---");
        List<Course> courses = courseService.findAll();
        if (courses.isEmpty()) {
            Console.println("No courses found.");
        } else {
            courses.forEach(Console::println);
        }
    }

    private void update() {
        Console.println();
        Console.println("--- Update Course ---");
        Long id = Console.readLong("Enter id: ");
        try {
            CourseDTO dto = readCourseData();
            Course course = courseService.update(id, dto);
            Console.println("Course updated: " + course);
        } catch (BusinessException e) {
            printError(e);
        }
    }

    private void delete() {
        Console.println();
        Console.println("--- Delete Course ---");
        Long id = Console.readLong("Enter id: ");
        try {
            courseService.delete(id);
            Console.println("Course deleted.");
        } catch (BusinessException e) {
            printError(e);
        }
    }

    private CourseDTO readCourseData() {
        String code = Console.readLine("Code: ").trim();
        String name = Console.readLine("Name: ").trim();
        String description = Console.readLine("Description: ").trim();
        Integer maxCapacity = Console.readInt("Max capacity: ");
        return new CourseDTO(code, name, description, maxCapacity);
    }

    private void printError(BusinessException e) {
        Console.println("Error: " + e.getMessage());
    }
}
