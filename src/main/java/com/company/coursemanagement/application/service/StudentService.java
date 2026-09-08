package com.company.coursemanagement.application.service;

import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student save(Student student);

    Optional<Student> findByIdOptional(Long id);

    Student findById(Long id);

    List<Student> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);
}