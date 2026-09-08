package com.company.coursemanagement.application.service;

import com.company.coursemanagement.domain.exception.*;
import com.company.coursemanagement.domain.model.*;
import com.company.coursemanagement.domain.repository.CourseRepository;
import com.company.coursemanagement.domain.repository.EnrollmentRepository;
import com.company.coursemanagement.domain.repository.StudentRepository;

import java.util.List;

public interface EnrollmentService {
    Enrollment enroll(Long studentId, Long courseId);

    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findByCourseId(Long courseId);

    List<Enrollment> findAll();

    void cancel(Long id);
}