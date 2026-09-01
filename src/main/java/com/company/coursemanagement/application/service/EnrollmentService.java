package com.company.coursemanagement.application.service;

import com.company.coursemanagement.domain.exception.*;
import com.company.coursemanagement.domain.model.*;
import com.company.coursemanagement.domain.repository.CourseRepository;
import com.company.coursemanagement.domain.repository.EnrollmentRepository;
import com.company.coursemanagement.domain.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Enrollment enroll(Long studentId, Long courseId) {
        if (!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException("Student not found with id: " + studentId);
        }
        if (!courseRepository.existsById(courseId)) {
            throw new CourseNotFoundException("Course not found with id: " + courseId);
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + courseId));

        long enrolled = enrollmentRepository.findByCourseId(courseId).stream()
                .filter(e -> e.getStatus() == EnrollmentStatus.ACTIVE)
                .count();

        if (enrolled >= course.getMaxCapacity()) {
            throw new BusinessException("Course has reached its maximum capacity");
        }

        Enrollment enrollment = new Enrollment(studentId, courseId, LocalDate.now(), EnrollmentStatus.ACTIVE);
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> findByStudentId(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> findByCourseId(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public void cancel(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() ->
                        new EnrollmentNotFoundException("Enrollment not found with id: " + id));
        enrollmentRepository.deleteById(id);
    }
}
