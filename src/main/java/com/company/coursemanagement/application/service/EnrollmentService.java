package com.company.coursemanagement.application.service;

import com.company.coursemanagement.application.dto.EnrollmentDTO;
import com.company.coursemanagement.domain.exception.CourseNotFoundException;
import com.company.coursemanagement.domain.exception.EnrollmentNotFoundException;
import com.company.coursemanagement.domain.exception.StudentNotFoundException;
import com.company.coursemanagement.domain.model.Enrollment;
import com.company.coursemanagement.domain.model.EnrollmentStatus;
import com.company.coursemanagement.domain.repository.CourseRepository;
import com.company.coursemanagement.domain.repository.EnrollmentRepository;
import com.company.coursemanagement.domain.repository.StudentRepository;

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

    public Enrollment create(EnrollmentDTO dto) {
        if (!studentRepository.existsById(dto.studentId())) {
            throw new StudentNotFoundException(dto.studentId());
        }
        if (!courseRepository.existsById(dto.courseId())) {
            throw new CourseNotFoundException(dto.courseId());
        }
        Enrollment enrollment = new Enrollment(dto.studentId(), dto.courseId(),
                dto.enrollmentDate(), EnrollmentStatus.ACTIVE);
        return enrollmentRepository.save(enrollment);
    }

    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException(id));
    }

    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    public Enrollment cancel(Long id) {
        Enrollment existing = findById(id);
        if (existing.getStatus() == EnrollmentStatus.CANCELLED) {
            throw new IllegalStateException("Enrollment already cancelled: " + id);
        }
        Enrollment cancelled = existing.withStatus(EnrollmentStatus.CANCELLED);
        return enrollmentRepository.save(cancelled);
    }

    public void delete(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException(id);
        }
        enrollmentRepository.deleteById(id);
    }
}
