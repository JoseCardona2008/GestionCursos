package com.company.coursemanagement.application.service;

import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.model.Course;
import com.company.coursemanagement.domain.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Course save(Course course);

    Optional<Course> findById(Long id);

    Optional<Course> findByCode(String code);

    List<Course> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);
}