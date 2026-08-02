package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.StudentDTO;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(StudentDTO dto) {
        Student student = new Student(dto.firstName(), dto.lastName(), dto.email(), dto.birthDate());
        return studentRepository.save(student);
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student update(Long id, StudentDTO dto) {
        Student existing = findById(id);
        Student updated = new Student(existing.getId(), dto.firstName(), dto.lastName(),
                dto.email(), dto.birthDate());
        return studentRepository.save(updated);
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }
}
