package com.company.coursemanagement.presentation.controller;

<<<<<<< HEAD
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.application.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
=======
import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.presentation.dto.request.CreateStudentRequestDto;
import com.company.coursemanagement.presentation.dto.response.CreateStudentResponseDto;
import java.util.List;
>>>>>>> c4ff006 (Implementacion de var, try-catch en controllers y creacion de Request/Response DTOs)
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        try {
            List<Student> students = studentService.findAll();
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        try {
            Student student = studentService.findById(id);
            return ResponseEntity.ok(student);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
=======
    public Student getStudent(@PathVariable Long id) {
        try {
            var student = studentService.findById(id);
            return student;
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
>>>>>>> c4ff006 (Implementacion de var, try-catch en controllers y creacion de Request/Response DTOs)
        }
    }

    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        try {
            Student saved = studentService.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
=======
    public CreateStudentResponseDto createStudent(@RequestBody CreateStudentRequestDto request) {
        try {
            var student = new Student(request.firstName(), request.lastName(), request.email(), request.birthDate());
            var saved = studentService.save(student);
            return CreateStudentResponseDto.from(saved);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
>>>>>>> c4ff006 (Implementacion de var, try-catch en controllers y creacion de Request/Response DTOs)
        }
    }

    @DeleteMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (StudentNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
=======
    public void deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
>>>>>>> c4ff006 (Implementacion de var, try-catch en controllers y creacion de Request/Response DTOs)
        }
    }
}
