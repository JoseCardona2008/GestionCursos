package com.company.coursemanagement.application.service.impl;

import com.company.coursemanagement.application.service.StudentService;
import com.company.coursemanagement.domain.exception.DuplicateIdException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        if (student.getId() != null && studentRepository.existsById(student.getId())) {
            throw new DuplicateIdException("Student ID already exists: " + student.getId());
        }
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> findByIdOptional(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found with id: " + id));
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Student not found with id: " + id));
        studentRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }
}