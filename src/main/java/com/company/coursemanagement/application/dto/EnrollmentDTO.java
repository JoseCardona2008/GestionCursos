package com.company.coursemanagement.application.dto;

import java.time.LocalDate;

public record EnrollmentDTO(Long studentId, Long courseId, LocalDate enrollmentDate) {
}
