package com.company.coursemanagement.infrastructure.repository;

import com.company.coursemanagement.infrastructure.entity.CourseEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseJpaRepository extends JpaRepository<CourseEntity, Long> {
    Optional<CourseEntity> findByCode(String code);
}
