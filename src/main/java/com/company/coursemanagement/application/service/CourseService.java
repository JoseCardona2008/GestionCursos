package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.CourseDTO;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.model.Course;
import com.company.coursemanagement.domain.repository.CourseRepository;

import java.util.List;

public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course create(CourseDTO dto) {
        Course course = new Course(dto.code(), dto.name(), dto.description(), dto.maxCapacity());
        return courseRepository.save(course);
    }

    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public Course update(Long id, CourseDTO dto) {
        Course existing = findById(id);
        Course updated = new Course(existing.getId(), dto.code(), dto.name(),
                dto.description(), dto.maxCapacity());
        return courseRepository.save(updated);
    }

    public void delete(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
    }
}
