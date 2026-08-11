package com.company.coursemanagement.application.service;

import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Student;
import com.company.coursemanagement.domain.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student guardar(Student student) {
        if (student.getId() != null && studentRepository.existsById(student.getId())) {
            throw new IllegalArgumentException("El ID del estudiante ya existe");
        }
        return studentRepository.save(student);
    }

    public Optional<Student> buscarPorId(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> listarTodos() {
        return studentRepository.findAll();
    }

    public void eliminarPorId(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException("Estudiante no encontrado con id: " + id));
        studentRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return studentRepository.existsById(id);
    }
}