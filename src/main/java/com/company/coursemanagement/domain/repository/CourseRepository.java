package com.company.coursemanagement.domain.repository;

import com.company.coursemanagement.domain.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course save(Course course);

    Optional<Course> findById(Long id);

    Optional<Course> findByCode(String code);

    List<Course> findAll();

    void deleteById(Long id);

    boolean existsById(Long id);
}
