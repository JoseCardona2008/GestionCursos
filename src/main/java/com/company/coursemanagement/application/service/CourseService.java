package com.company.coursemanagement.application.service;

import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.model.Course;
import com.company.coursemanagement.domain.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course guardar(Course course) {
        if (courseRepository.findByCode(course.getCode()).isPresent()) {
            throw new IllegalArgumentException("El codigo del curso ya existe");
        }
        return courseRepository.save(course);
    }

    public Optional<Course> buscarPorId(Long id) {
        return courseRepository.findById(id);
    }

    public Optional<Course> buscarPorCodigo(String code) {
        return courseRepository.findByCode(code);
    }

    public List<Course> listarTodos() {
        return courseRepository.findAll();
    }

    public void eliminarPorId(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException("Curso no encontrado con id: " + id));
        courseRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return courseRepository.existsById(id);
    }
}