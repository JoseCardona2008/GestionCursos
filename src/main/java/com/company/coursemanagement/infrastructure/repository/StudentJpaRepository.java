package com.company.coursemanagement.infrastructure.repository;

import com.company.coursemanagement.infrastructure.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<StudentEntity, Long> {
}
