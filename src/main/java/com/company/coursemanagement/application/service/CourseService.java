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

    public Course save(Course course) {
        if (courseRepository.findByCode(course.getCode()).isPresent()) {
            throw new IllegalArgumentException("Course code already exists");
        }
        return courseRepository.save(course);
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Optional<Course> findByCode(String code) {
        return courseRepository.findByCode(code);
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public void deleteById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new CourseNotFoundException("Course not found with id: " + id));
        courseRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }
}
