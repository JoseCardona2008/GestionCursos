package com.company.coursemanagement.infrastructure.repository;

import com.company.coursemanagement.infrastructure.entity.EnrollmentEntity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentJpaRepository extends JpaRepository<EnrollmentEntity, Long> {
    List<EnrollmentEntity> findByStudentId(Long studentId);
    List<EnrollmentEntity> findByCourseId(Long courseId);
}
