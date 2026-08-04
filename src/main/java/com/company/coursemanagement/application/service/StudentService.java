package com.company.coursemanagement.application.service;

import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;
import com.company.coursemanagement.domain.repository.InMemoryStudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService implements StudentRepository {

    private final List<Student> students;

    public StudentService(InMemoryStudentRepository studentRepository) {
        this.students = new ArrayList<>();
    }

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                students.set(i, student); // Actualizar
                return student;
            }
        }

        students.add(student); // Guardar nuevo
        return student;
    }

    @Override
    public Optional<Student> findById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public void deleteById(Long id) {
        Student student = findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id));

        students.remove(student);
    }

    @Override
    public boolean existsById(Long id) {
        return students.stream()
                .anyMatch(student -> student.getId().equals(id));
    }
}