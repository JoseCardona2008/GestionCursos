package com.company.coursemanagement.presentation.dto.response;

import java.time.LocalDate;

public record CreateEnrollmentResponseDto(
        Long id,
        Long studentId,
        Long courseId,
        LocalDate enrollmentDate,
        com.company.coursemanagement.domain.model.EnrollmentStatus status
) {
    public static CreateEnrollmentResponseDto from(com.company.coursemanagement.domain.model.Enrollment enrollment) {
        return new CreateEnrollmentResponseDto(
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getCourseId(),
                enrollment.getEnrollmentDate(),
                enrollment.getStatus()
        );
    }
}